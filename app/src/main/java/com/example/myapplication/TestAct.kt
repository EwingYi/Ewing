package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 *by:   YangYi
 *date: 2020-03-29
 */
@SuppressLint("Registered")
class TestAct: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getView()
    }

    private fun getView(view: View? = null) {

    }
}