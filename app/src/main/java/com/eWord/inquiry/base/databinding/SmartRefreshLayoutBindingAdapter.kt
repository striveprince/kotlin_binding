package com.eWord.inquiry.base.databinding

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.InverseBindingMethod
import androidx.databinding.InverseBindingMethods
import androidx.databinding.adapters.ListenerUtil
import com.eWord.inquiry.R
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener

/**
 * Company:
 * Description:
 * Author: created by ArvinWang on 2019/10/10 12:16
 * Email: 1033144294@qq.com
 */

@InverseBindingMethods(
    InverseBindingMethod(
        type = SmartRefreshLayout::class,
        attribute = "refreshing",
        event = "refreshingAttrChanged",
        method = "isRefreshing"
    )
)
object SmartRefreshLayoutBindingAdapter{
    @BindingAdapter("refreshing")
    fun setRefreshing(view: SmartRefreshLayout, refreshing: Boolean) {
        if (refreshing != view.isRefreshing&&refreshing!=view.isLoading) {
            view.autoRefresh()
        }
    }
//
//    @BindingAdapter(value = ["onRefreshListener", "refreshingAttrChanged"], requireAll = false)
//    fun setOnRefreshListener(view: SmartRefreshLayout, listener: OnRefreshListener?, refreshingAttrChanged: InverseBindingListener?) {
//        val newValue = OnRefreshListener{
//            refreshingAttrChanged?.onChange()
//            listener?.onRefresh(view)
//        }
//        val oldValue = ListenerUtil.trackListener<OnRefreshListener>(
//            view,
//            newValue,
//            R.id.smart_refresh_layout
//        )
//        if (oldValue != null) view.setOnRefreshListener(null)
//        view.setOnRefreshListener(newValue)
//    }

}