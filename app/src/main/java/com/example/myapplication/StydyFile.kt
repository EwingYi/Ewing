package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *by:   YangYi
 *date: 2020/5/10
 */

/**
 * class TestForExtendAct: TestAct() {}
 * 这样写会报错
 * 原因是 Kotlin 里的类默认是 final 的，而 Java 里只有加了 final  关键字的类才是 final 的
 * 可以使用 open 来做这件事 把要继承的那个类 class前缀加open
 * 但是A继承B B是open的 A还是final的 open 没有父类到子类的遗传性
 * 如果一些地方需要 消除掉父类到子类的遗传性 就在前面加个final 关闭遗传性
 */
class TstAct1 : AppCompatActivity() {
    // 👇onCreate 仍然是 override 的
    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        ...
    }

    fun tstFun(){

    }
}


/**
 * Kotlin 里除了新增了 open 关键字之外，也有和 Java 一样的 abstract 关键字
 * 这俩关键字的区别就是 abstract 关键字修饰的类无法直接实例化，并且通常来说会和 abstract 修饰的函数一起出现，当然，也可以没有这个 abstract 函数
 */
abstract class TstAct2 : AppCompatActivity() {
    abstract fun test()
}
//但是子类如果要实例化，还是需要实现这个 abstract 函数的：
class TstAct3 : TstAct2() {
    override fun test() {}
}


/**
 * Kotlin的 is&as
 */
fun tstIs(){
    val activity = TstAct1()
    if (activity is TstAct1) {
        // 强转由于类型推断被省略了
        activity.tstFun()
    }

    //如果转错了会报错
    (activity as TstAct1).tstFun()

    //进行安全的强转，可以更优雅地处理强转出错的情况
    (activity as? TstAct1)?.tstFun()//它的意思就是说如果强转成功就执行之后的调用，如果强转不成功就不执行
}