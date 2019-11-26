package com.lifecycle.binding.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.lifecycle.binding.inter.bind.annotation.LayoutView

/**
 * Company:
 * Description:
 * Author: created by ArvinWang on 2019/11/14 18:04
 * Email: 1033144294@qq.com
 */

fun<T> LiveData<T>.observer(owner: LifecycleOwner,block:(T)->Unit){
    observe(owner, Observer { block(it) })
}

fun findLayoutView(thisCls: Class<*>): LayoutView {
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    return thisCls.getAnnotation(LayoutView::class.java)
        ?: return findLayoutView(thisCls = thisCls.superclass!!)
}