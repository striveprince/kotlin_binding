package com.lifecycle.binding.inter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.lifecycle.binding.util.findLayoutView

interface Inflate<Holder>{
    fun setHolder(holder: Holder)
    fun layoutId():Int= findLayoutView(javaClass).layout[0]
    fun createView(context: Context, v: ViewGroup,binding:Any?): View
}