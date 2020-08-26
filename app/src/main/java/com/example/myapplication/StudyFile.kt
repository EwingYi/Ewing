package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

/**
 *by:   YangYi
 *date: 2020/8/1
 */
class sGetSet {
    var name = "Mike"
    fun run() {
        name = "Mary"
        // 👆的写法实际上是👇这么调用的
        // setName("Mary")
        // 建议自己试试，IDE 的代码补全功能会在你打出 setn 的时候直接提示 name 而不是 setName

        println(name)
        // 👆的写法实际上是👇这么调用的
        // print(getName())
        // IDE 的代码补全功能会在你打出 getn 的时候直接提示 name 而不是 getName
    }

    var mGetSet = ""
        get() {
            return field + "这是get"
        }
        set(value) {
            field = "set$value"
        }
}

open class canExtendActivity : AppCompatActivity() {}
class myActivity : canExtendActivity() {}
/**如果想让自己写的Act被继承 在kotlin中 加上open 但是继承之后的Act还是不能被继承 因为open 没有父类到子类的遗传性
 * 但是override有遗传性 如果不希望override的方法有遗传性 加上final就可以了*/


/**abstract 关键字修饰的类无法直接实例化*/
abstract class AbstractActivity : AppCompatActivity() {}
//fun abstractFun(){ var a = AbstractActivity() } //这样不可以


/**类型的判断和强转
 * is 类型判断 因为编译器能够进行类型推断,可以帮助我们省略强转的写法
 * as 不进行类型判断,直接进行强转调用 (这种写法如果强转类型操作是正确的当然没问题,但如果强转成一个错误的类型,程序就会抛出一个异常)
 * as? 因为as如果强壮错误 就会异常 有了as? 如果强转成功就执行之后的调用,如果强转不成功就不执行*/


/**final*/
//var 是 variable 的缩写， val 是 value 的缩写
/**final 变成了 val。
Kotlin 函数参数默认是 val 类型，所以参数前不需要写 val 关键字，Kotlin 里这样设计的原因是保证了参数不会被修改，而 Java 的参数可修改（默认没 final 修饰）会增加出错的概率。*/
fun finalTest() {
    val fina1 = 1

    //           👇 参数是没有 val 的
    fun method(final2: String) {
        val final3 = "The parameter is $final2"
    }
}

class ValTest() {
    //val 和 final 还是有一点区别的，虽然 val 修饰的变量不能二次赋值，但可以通过自定义变量的 getter 函数，让变量每次被访问时，返回动态获取的值
    private val items = mutableListOf<String>()

    val size: Int
        get() { // 👈 每次获取 size 值时都会执行 items.size
            return items.size
        }
}


/**Kotlin Object*/
/**
Kotlin 里的 object ——首字母小写的，不是大写，Java 里的 Object 在 Kotlin 里不用了。
Java 中的 Object 在 Kotlin 中变成了 Any，和 Object 作用一样：作为所有类的基类。
而 object 不是类，像 class 一样在 Kotlin 中属于关键字
 */
//它的意思很直接：创建一个类，并且创建一个这个类的对象。这个就是 object 的意思:对象
object Sample {
    val name = "A name"
}

//在代码中如果要使用这个对象，直接通过它的类名就可以访问：
fun getSample() {
    Sample.name
}
//其实就是单例了已经


/**单例*/
//Java
//public class A {
//    private static A sInstance;
//
//    public static A getInstance() {
//        if (sInstance == null) {
//            sInstance = new A();
//        }
//        return sInstance;
//    }
//
//    // 👇还有很多模板代码
//    ...
//}
//Kotlin
// 👇 class 替换成了 object
object DanLi {
    val number: Int = 1
    fun method() {
        println("A.method()")
    }
}
/**不同点是和类的定义类似，但是把 class 换成了 object 。
不需要额外维护一个实例变量 sInstance。
不需要「保证实例只创建一次」的 getInstance() 方法。*/

//******这种通过 object 实现的单例是一个饿汉式的单例，并且实现了线程安全。


/**继承类和实现接口*/
//Kotlin 中不仅类可以继承别的类，可以实现接口，object 也可以
open class A {
    open fun method() {}
}

interface B {
    fun interfaceMethod()
}

object C : A(), B {

    override fun method() {}

    override fun interfaceMethod() {}
}
//为什么 object 可以实现接口呢？简单来讲 object 其实是把两步合并成了一步，既有 class 关键字的功能，又实现了单例，这样就容易理解了

/**匿名类*/
val listener = object : ViewPager.SimpleOnPageChangeListener() {
    override fun onPageSelected(position: Int) {
        // override
    }
}
/**Java 中 new 用来创建一个匿名类的对象
Kotlin 中 object: 也可以用来创建匿名类的对象
这里的 new  和 object: 修饰的都是接口或者抽象类*/


/**companion object*/
class Companion {
    companion object B {
        var c: Int = 0
    }
}

//companion 可以理解为伴随、伴生，表示修饰的对象和外部类绑定
//一个类中最多只可以有一个伴生对象
//companion object就是一个伴生对象 对象里可以有多个变量
//这种伴生对象在调用的时候:
fun getCompanionObj() {
    Companion.c
}

//所以，当有 companion 修饰时，对象的名字也可以省略掉
class CompanionChange {
    //              👇 B 没了
    companion object {
        var c: Int = 0
    }
}

/**静态初始化*/
//Java 中的静态变量和方法，在 Kotlin 中都放在了 companion object 中。因此 Java 中的静态初始化在 Kotlin 中自然也是放在 companion object 中的，像类的初始化代码一样，由 init 和一对大括号表示
class JingTaiClass {
    companion object {
        init {
        }
    }
}


/**top-level property / function 声明
 * 当前这种file问价写的方法 就是top-level property / function 声明
 * 这样写的属性和函数，不属于任何 class，而是直接属于 package，它和静态变量、静态函数一样是全局的，但用起来更方便：你在其它地方用的时候，就连类名都不用写*/

//如果存在命名相同的顶级函数
//在使用的时候如果同时调用这两个同名函数 IDE 会自动加上包前缀来区分，这也印证了「顶级函数属于包」的特性
//import org.kotlinmaster.library1.method
//fun test() {
//    method()
//    org.kotlinmaster.library2.method()
//}


//在实际使用中，在 object、companion object 和 top-level 中该选择哪一个呢
/**如果想写工具类的功能，直接创建文件，写 top-level「顶层」函数。
如果需要继承别的类或者实现接口，就用 object 或 companion object。*/


/**常量*/
//static
//java中声明常量: public static final int CONST_NUMBER = 1;
//Kotlin 中声明常量
class StaticTest {
    companion object {
        const val CONST_NUMBER = 1
    }
}
/**不同点:
 * Kotlin 的常量必须声明在对象（包括伴生对象）或者「top-level 顶层」中，因为常量是静态的。
 * Kotlin 新增了修饰常量的 const 关键字。*/
//除此之外还有一个区别: Kotlin 中只有基本类型和 String 类型可以声明成常量


/**init*/
/**Kotlin 的 init 代码块和 Java 一样，都在实例化时执行，并且执行顺序都在构造器之前*/
class User {
    init {
        // 初始化代码块，先于下面的构造器执行
    }

    constructor() {
    }
}

//Kotlin 和 Java 一样有三种集合类型：List、Set 和 Map，它们的含义分别如下：
//List 以固定顺序存储一组元素，元素可以重复。
//Set 存储一组互不相等的元素，通常没有固定顺序。
//Map 存储 键-值 对的数据集合，键互不相等，但不同的键可以对应相同的值。

//Kotlin 中的 List 多了一个特性：支持 covariant（协变）。也就是说，可以把子类的 List 赋值给父类的 List 变量 。但是数组不支持协变。但是相反的，java的数组可以支持协变，list不支持协变
fun xieBianFun() {
    val strs: List<String> = listOf("a", "b", "c")
    val anys: List<Any> = strs // success
}

//和数组的区别
//Kotlin 中数组和 MutableList 的 API 是非常像的，主要的区别是数组的元素个数不能变。那在什么时候用数组呢？
//这个问题在 Java 中就存在了，数组和 List 的功能类似，List 的功能更多一些，直觉上应该用 List 。但数组也不是没有优势，基本类型 (int[]、float[]) 的数组不用自动装箱，性能好一点。
//在 Kotlin 中也是同样的道理，在一些性能需求比较苛刻的场景，并且元素类型是基本类型时，用数组好一点。不过这里要注意一点，Kotlin 中要用专门的基本类型数组类 (IntArray FloatArray LongArray) 才可以免于装箱。也就是说元素不是基本类型时，相比 Array，用 List 更方便些。

fun kotlinCreateMap() {
//    Kotlin 中创建一个 Map：
    val map = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 3)
//    mapOf 的每个参数表示一个键值对，to 表示将「键」和「值」关联
}

//可变集合/不可变集合
//上面修改 Map 值的例子中，创建函数用的是 mutableMapOf() 而不是 mapOf()，因为只有 mutableMapOf() 创建的 Map 才可以修改。Kotlin 中集合分为两种类型：只读的和可变的。这里的只读有两层意思：
//集合的 size 不可变
//集合中的元素值不可变
//以下是三种集合类型创建不可变和可变实例的例子：
//listOf() 创建不可变的 List，mutableListOf() 创建可变的 List。
//setOf() 创建不可变的 Set，mutableSetOf() 创建可变的 Set。
//mapOf() 创建不可变的 Map，mutableMapOf() 创建可变的 Map。
//可以看到，有 mutable 前缀的函数创建的是可变的集合，没有 mutbale 前缀的创建的是不可变的集合，不过不可变的可以通过 toMutable*() 系函数转换成可变的集合：


//Kotlin 中有四种可见性修饰符：
//public ：公开，可见性最大，哪里都可以引用。
//private：私有，可见性最小，根据声明位置不同可分为类中可见和文件中可见。
//protected：保护，相当于 private + 子类可见。
//internal：内部，仅对 module 内可见。
//相比 Java 少了一个 default 「包内可见」修饰符，多了一个 internal「module 内可见」修饰符

//Java 的 default「包内可见」在 Kotlin 中被弃用掉了，Kotlin 中与它最接近的可见性修饰符是 internal「module 内可见」。为什么会弃用掉包内可见呢？我觉得有这几个原因：
//Kotlin 鼓励创建 top-level 函数和属性，一个源码文件可以包含多个类，使得 Kotlin 的源码结构更加扁平化，包结构不再像 Java 中那么重要。
//为了代码的解耦和可维护性，module 越来越多、越来越小，使得 internal 「module 内可见」已经可以满足对于代码封装的需求。


/**类的初始化*/
/**以下两种 同理 构造器可以简写 编译器推荐第二种写法
 * 通常情况下 主构造器中的 constructor 关键字可以省略
 * 如果在主构造器的参数声明时加上 var 或者 val，就等价于在类中创建了该名称的属性（property），并且初始值就是主构造器中该参数的值*/
class constructorAct {
    var name: String

    constructor(name: String) {
        this.name = name
    }
}

class constructorActChange(var name: String) {}

/**当一个类中同时有主构造器与次构造器的时候 根据调用时候的传值推断你调用的是哪个构造器*/
//主构造器
class User1 constructor(var name: String) {
    // 👇  👇 直接调用主构造器
    //次构造器
    constructor(name: String, id: Int) : this(name) {
    }

    // 👇 通过上一个次构造器，间接调用主构造器
    //次构造器
    constructor(name: String, id: Int, age: Int) : this(name, id) {
    }
}

/**有些场景，constructor 是不可以省略的，例如在主构造器上使用「可见性修饰符」或者「注解」*/
class User2 private constructor(name: String) {
//           👆 主构造器被修饰为私有的，外部就无法调用该构造器
}

//总结
//首先创建一个 User 类：
//class User {
//}
//
//添加一个参数为 name 与 id 的主构造器：
//class User(name: String, id: String) {
//}
//
//将主构造器中的 name 与 id 声明为类的属性：
//class User(val name: String, val id: String) {
//}
//
//然后在 init 代码块中添加一些初始化逻辑：
//class User(val name: String, val id: String) {
//    init {
//        ...
//    }
//}
//
//最后再添加其他次构造器：
//class User(val name: String, val id: String) {
//    init {
//        ...
//    }
//
//    constructor(person: Person) : this(person.name, person.id) {
//    }
//}
//当一个类有多个构造器时，只需要把最基本、最通用的那个写成主构造器就行了。这里我们选择将参数为 name 与 id 的构造器作为主构造器。


/**参数默认值*/
//Java 中，允许在一个类中定义多个名称相同的方法，但是参数的类型或个数必须不同，这就是方法的重载：
//public void sayHi(String name) {
//    System.out.println("Hi " + name);
//}
//public void sayHi() {
//    sayHi("world");
//}

//在 Kotlin 中，也可以使用这样的方式进行函数的重载，不过还有一种更简单的方式，那就是「参数默认值」:
fun sayHi(name: String = "world") = println("Hi $name")

//当调用 sayHi 函数时，参数是可选的:
fun sayHiTest() {
    sayHi("kaixue.io")
    sayHi() // 使用了默认值 "world"
}


fun sayHi(name: String = "world", age: Int) {}

/**命名参数*/
fun sayHiTestForMingMing() {
    sayHi(age = 21)
}

/**位置参数 按位置顺序进行参数填写
 * 当一个函数被调用时，如果混用位置参数与命名参数，那么所有的位置参数都应该放在第一个命名参数之前*/
fun sayHiTestForWeiZhi() {
//    sayHi(name = "wo", 21) // 👈 IDE 会报错，Mixing named and positioned arguments is not allowed
    sayHi("wo", age = 21) // 👈 这是正确的写法
}

/**嵌套函数*/
fun login(user: String, password: String, illegalStr: String) {
    fun validate(value: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(illegalStr)
        }
    }
    validate(user)
    validate(password)
}


/**字符串模板*/
fun stringMoBan() {
    val name = "world"
    //用 '$' 符号加参数的方式
    println("Hi $name")
    //除了变量，$ 后还可以跟表达式，但表达式是一个整体，所以我们要用 {} 给它包起来
    println("Hi ${name.length}")

    //使用转义字符 \n 进行换行操作
    val nameN = "world!\n"
    println("Hi $nameN") // 👈 会多打一个空行

    //在 Android 的资源文件里，定义字符串也有类似用法
//    <string name="hi">Hi %s</string>
//    getString(R.id.hi, "world");
}

/**raw string (原生字符串)*/
//有时候我们不希望写过多的转义字符，这种情况 Kotlin 通过「原生字符串」来实现
//用法就是使用一对 """ 将字符串括起来
fun stringRaw() {
    /**
     * \n 并不会被转义
    最后输出的内容与写的内容完全一致，包括实际的换行
    $ 符号引用变量仍然生效
     */
    val name = "world"
    val myName = "kotlin"
    val text = """
      Hi $name!
    My name is $myName.\n
"""
    println(text)
    //输出结果
//    Hi world!
//  My name is kotlin.\n

    //对齐方式看起来不太优雅，原生字符串还可以通过 trimMargin() 函数去除每行前面的空格
    val text1 = """
     👇 
      |Hi world!
    |My name is kotlin.
""".trimMargin()
    println(text)
//    输出结果
//    Hi world!
//    My name is kotlin.
    /**
    | 符号为默认的边界前缀，前面只能有空格，否则不会生效
    输出时 | 符号以及它前面的空格都会被删除
    边界前缀还可以使用其他字符，比如 trimMargin("/")，只不过上方的代码使用的是参数默认值的调用方式
     */
}


/**数组与集合的操作符*/
fun listStudy() {
    val intArray = intArrayOf(1, 2, 3)
    val strList = listOf("a", "b", "c")

    //forEach：遍历每一个元素
    //              👇 lambda 表达式，i 表示数组的每个元素
    intArray.forEach { i ->
        print("$i ")
    }
    // 输出：1 2 3

    //filter：对每个元素进行过滤操作，如果 lambda 表达式中的条件成立则留下该元素，否则剔除，最终生成新的集合
    // [1, 2, 3]
    //  {2, 3}
    //            👇 注意，这里变成了 List
    val filterList = intArray.filter { i ->
        i != 1 // 👈 过滤掉数组中等于 1 的元素
    }

    //map：遍历每个元素并执行给定表达式，最终形成新的集合
    //  [1, 2, 3]
    //  {2, 3, 4}
    val mapList = intArray.map { i ->
        i + 1 // 👈 每个元素加 1
    }

    //flatMap：遍历每个元素，并为每个元素创建新的集合，最后合并到一个集合中
    //          [1, 2, 3]
    // {"2", "a" , "3", "a", "4", "a"}
    intArray.flatMap { i ->
        listOf("${i + 1}", "a") // 👈 生成新集合
    }


    //Range
    //Kotlin 中的 Range 表示区间的意思，也就是范围

    //从 0 到 1000 的范围，包括 1000，数学上称为闭区间 [0, 1000]
    val range: IntRange = 0..1000
    //从 0 到 1000，但不包括 1000，这就是半开区间 [0, 1000)
    val range1: IntRange = 0 until 1000

    //Range一般都是用来遍历的
    //          👇 默认步长为 1，输出：0, 1, 2, 3, 4, 5, 6, 7....1000,
    for (i in range) {
        print("$i, ")
    }
    //除了使用默认的步长 1，还可以通过 step 设置步长
    //                    👇 步长为 2，输出：0, 2, 4, 6, 8, 10,....1000,
    for (i in range step 2) {
        print("$i, ")
    }
    //以上是递增区间，Kotlin 还提供了递减区间 downTo ，不过递减没有半开区间的用法
    //            👇 输出：4, 3, 2, 1,
    for (i in 4 downTo 1) {
        print("$i, ")
    }
    //这里的 downTo 以及上面的 step 都叫做「中缀表达式」
}

/**Sequence*/
//了解 Sequence 序列的使用方式
fun sequenceFun() {
    //序列 Sequence 又被称为「惰性集合操作」
    val sequence = sequenceOf(1, 2, 3, 4)
    val result: Sequence<Int> = sequence
        .map { i ->
            println("Map $i")
            i * 2
        }
        .filter { i ->
            println("Filter $i")
            i % 3 == 0
        }

    //惰性的概念首先就是说在打印之前的代码运行时不会立即执行，它只是定义了一个执行流程，只有 result 被使用到的时候才会执行
    println(result.first()) // 👈 只取集合的第一个元素

    //println 执行时数据处理流程是这样的：
    //取出元素 1 -> map 为 2 -> filter 判断 2 是否能被 3 整除
    //取出元素 2 -> map 为 4 -> filter 判断 4 是否能被 3 整除
    //...
    //惰性指当出现满足条件的第一个元素的时候，Sequence 就不会执行后面的元素遍历了，即跳过了 4 的遍历。
}
//List 是没有惰性的特性的

//Sequence 这种类似懒加载的实现有下面这些优点：
//一旦满足遍历退出的条件，就可以省略后续不必要的遍历过程。
//像 List 这种实现 Iterable 接口的集合类，每调用一次函数就会生成一个新的 Iterable，下一个函数再基于新的 Iterable 执行，每次函数调用产生的临时 Iterable 会导致额外的内存消耗，而 Sequence 在整个流程中只有一个。
//因此，Sequence 这种数据类型可以在数据量比较大或者数据量未知的时候，作为流式处理的解决方案。

