package com.example.myapplication.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~         
 * Created by dev11 on 2019-08-13.
 * ==============================
 * 功能描述：
 *
 *
 */
class TestBean(type: Int) : MultiItemEntity {

    override fun getItemType(): Int {
        return itemType
    }

    companion object {
        val VIEW_HEAD = 99 // head
        val IMG_VIEW = 0
        val TEXT_VIEW = 1
        val IMG_TEXT_VIEW = 2

    }
}