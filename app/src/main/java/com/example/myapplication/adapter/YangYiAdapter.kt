package com.example.myapplication.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.myapplication.R
import com.example.myapplication.bean.TestBeanForList

/**
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~         
 * Created by dev11 on 2019-08-13.
 * ==============================
 * 功能描述：
 *
 *
 */
class YangYiAdapter(layoutResId: Int = R.layout.list_item): BaseQuickAdapter<TestBeanForList, BaseViewHolder>(layoutResId) {
    override fun convert(helper: BaseViewHolder?, item: TestBeanForList?) {
        helper?.run {
            item?.let{
                with(it){
                    setText(R.id.test_text, name)
                }
            }
        }
    }

}