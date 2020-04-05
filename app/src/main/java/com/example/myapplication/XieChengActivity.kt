package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.YangYiAdapter
import com.example.myapplication.bean.TestBeanForList
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 *by:   YangYi
 *date: 2019-09-25
 */
class XieChengActivity : AppCompatActivity(), CoroutineScope by CoroutineScope(Dispatchers.Main) {
    /**
     * 协程没有想得那么高级 也没有想得那么难
     * 它只是烧你的cpu让你的app运行更加流畅而已
     * 协程不高级于线程 它只是 非阻塞式线程
     * 当然 用Runblocking 也可以让协程强行阻塞 但那只是根据自己需求 一般不建议用 会卡ui以及一些列操作
     * 当下能涉及到的
     * 1.上下文引用 用一个累去实行协程的接口 定义到主线程 这样方便你一个类中不用各种挂起协程 destory的时候cancel 就都清理掉了 方便维护
     * 2.launch 最基础的一种用法 他是满足线性执行 又可以中间随意切线程执行耗时或者ui操作
     * 3.async 多用于组合式挂起 通常如果两个耗时操作 互不相干 async可以让它并发 省时高效执行
     * 以下所有协程的方法 默认是主线程 因为我在activity实现的时候谢了*/


    private var man = 1
    private var woman = 2
    private val testAdapter = YangYiAdapter()
    private val testAdapterMan = YangYiAdapter()
    private val testAdapterWoman = YangYiAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val a = 1.11
        val testNum = a.div(1)
        Log.e("TAG",testNum.toString())
        launch_btn.setOnClickListener {
            initViewForLaunch()
        }
        async_btn.setOnClickListener {
            initViewForAsync()
        }
    }

    private fun initViewForAsync() {
        //async是在我们需要做耗时操作的同时 数据源很多 并且互不相干的情况调用 因为这样就不需要线性执行 省时高效
        var listDataMan = mutableListOf<TestBeanForList>()
        var listDataWoman = mutableListOf<TestBeanForList>()
        //这个launch因为我给activity加了协程的上下文 所以如果不加launch 就需要把所有方法加上suspend函数
        launch {
            val time = measureTimeMillis {
                val manList = async {
                    delay(2000)
                    getListData(100, man)
                }
                val womanList = async {
                    delay(1000)
                    getListData(1000, woman)
                }
                Log.e("获取数据 当前线程", Thread.currentThread().name)
                val asyncHint = "asycn 代码开始执行"
                test_text.text = asyncHint
                listDataMan = manList.await()
                listDataWoman = womanList.await()
            }

            Log.e("两个list加载耗时", time.toString())

            Log.e("更新list 当前线程", Thread.currentThread().name)
            test_list1.adapter = testAdapterMan
            test_list1.layoutManager = LinearLayoutManager(this@XieChengActivity)
            testAdapterMan.setNewData(listDataMan)

            test_list2.adapter = testAdapterWoman
            test_list2.layoutManager = LinearLayoutManager(this@XieChengActivity)
            testAdapterWoman.setNewData(listDataWoman)
        }

    }

    private fun initViewForLaunch() {
        //lanuch后我特意加的Main函数 方便我执行IO中的耗时操作之后 自动切回主线程
        //不要在launch里写 withcontext 一来写废了协程 二来那不是特么子弹头么 我写过的那种
        launch {
            Log.e("挂起launch函数 当前线程", Thread.currentThread().name)
            //UI一系列的不耗时操作就直接写
            val launchHint = "launch 代码开始执行"
            test_text.text = launchHint
            test_list1.layoutManager = LinearLayoutManager(this@XieChengActivity)
            test_list1.adapter = testAdapter
            //这一行看着是UI操作 在主线程 但由于我用了suspend+withcontext 获取数据会自动切到子线程做耗时操作
            //如果需要更新数据的时候 直接挂起一个launch 在里面调用带suspend的getListData就好 *带suspend的函数一定要在launch里面调用 他是协程很关键的一个东西
            val list = getListData(1000, man)

            Log.e("更新List 当前线程", Thread.currentThread().name)
            testAdapter.setNewData(list)
        }
    }

    private suspend fun getListData(listSize: Int, genderNum: Int): MutableList<TestBeanForList> {
        //这里用一个参数来接受协程子线程执行的耗时操作
        val List = withContext(Dispatchers.IO) {
            Log.e("加载数据 当前线程", Thread.currentThread().name)
            val testList = mutableListOf<TestBeanForList>()
            repeat(listSize) {
                val dataBean = TestBeanForList().apply {
                    when (genderNum) {
                        man -> {
                            name = "Ewing"
                            age = 22
                        }
                        woman -> {
                            name = "Lisa"
                            age = 20
                        }
                    }
                    gender = genderNum
                }
                testList.add(dataBean)
            }
            //最后一行为返回值 类似let run函数
            testList
        }
        //看上面操作 这样写返回的数据不会为空 类似唤醒协程 如若是一个类的私有变量返回  会为空(上方代码没执行完直接返回)
        return List
    }

    //如果是界面 不需要存储数据一类的 记得cancel
    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}