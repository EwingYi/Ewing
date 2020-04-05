package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.YangYiAdapter
import com.example.myapplication.bean.TestBean
import com.example.myapplication.bean.TestBeanForList
import kotlinx.android.synthetic.main.yangyi_layout.*

/**
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~         
 * Created by dev11 on 2019-08-12.
 * ==============================
 * 功能描述：
 *
 *
 */
class YangYiActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.yangyi_layout)
////        val test = Bundle()
////        test.putStringArrayList("test",list)
////        val intent = Intent(this, TestActivity::class.java)
////        intent.putExtra("Type", 123)
////        startActivity(intent,test)
//
//        test_btn.setOnClickListener {
//
//            val list = arrayListOf<String>()
//            repeat(200000){
//                list.add("****khfohwegwebswlhfp;wei")
//            }
//
////            val bundle = Bundle()
////            bundle.putStringArrayList("test",list)
//            val intent = Intent()
//            intent.setClass(this,TestActivity::class.java)
//            intent.putStringArrayListExtra("test",list)
//            startActivity(intent)
////            startActivity(TestActivity::class.java, bundle)
//        }
////        initView()
//    }
//
//    fun startActivity(clz: Class<*>, bundle: Bundle?) {
//        val intent = Intent()
//        intent.setClass(this, clz)
//        if (bundle != null) {
//            intent.putExtras(bundle)
//        }
//        startActivity(intent)
//    }
//
//    var mAdapter = YangYiAdapter()
//    private fun initView() {
//        val testList = mutableListOf<TestBeanForList>()
//        repeat(5){
//            Log.i("循环",it.toString())
//            testList.add(TestBeanForList())
//        }
//        testList.apply {
//            with(this[0]){
//                name = "yangyi"
//                age = 11
//                gender = 1
//            }
//            with(this[1]){
//                name = "Ewing"
//                age = 12
//                gender = 0
//            }
//            with(this[2]){
//                name = "XieXinYi"
//                age = 20
//                gender = 0
//            }
//            with(this[3]){
//                name = "Sam"
//                age = 14
//                gender = 1
//            }
//            with(this[4]){
//                name = "Kate"
//                age = 18
//                gender = 0
//            }
//        }
//        Log.i("testSize", testList.size.toString())
//        dataList.layoutManager = LinearLayoutManager(this)
//        dataList.adapter = mAdapter
//        mAdapter.setNewData(testList)
//    }
//
//    fun demoFilter(){
//        val list = mutableListOf<String>()
//        list.addAll(listOf("Ewing","Mariah","Ken","Ann","Taylor","Nicki","Relap","Jack","Edson"))
//        dataList.layoutManager = LinearLayoutManager(this)
//        dataList.adapter = mAdapter
////        mAdapter.setNewData(list)
//
//        search_text.addTextChangedListener(object : TextWatcher{
//            override fun afterTextChanged(p0: Editable?) {
//            }
//
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                val changeList = list.filter { it.contains(p0.toString()) }.toMutableList()
//                if(p0.toString() == ""){
////                    mAdapter.setNewData(list)
//                }else{
////                    mAdapter.setNewData(changeList)
//                }
//            }
//
//        })
//    }
}