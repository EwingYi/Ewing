package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val haha = 1553148406L * 1000L
        Log.e("haha", haha.toString())
        Log.e("2019-03-21", getThisTime(10, Date(haha.toLong())).toInt().toString())
    }

    fun getThisTime(value: Int, date: Date): String {
        var format: SimpleDateFormat? = null
        when (value) {
            1 -> format = SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 EEEE")
            2 -> format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            3 -> format = SimpleDateFormat("yyyy/MM/dd")
            4 -> format = SimpleDateFormat("HH:mm:ss")
            5 -> format = SimpleDateFormat("EEEE")
            6 -> format = SimpleDateFormat("E")
            7 -> format = SimpleDateFormat("MM-dd")
            8 -> format = SimpleDateFormat("EEEE", Locale.ENGLISH)
            9 -> format = SimpleDateFormat("E", Locale.ENGLISH)
            10 -> format = SimpleDateFormat("yyyyMMdd")
            else -> {
            }
        }
        return format!!.format(date)
    }
}
