package com.lifecycle.binding.inter.inflate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lifecycle.binding.Constant

interface RecyclerInflate: Inflate<RecyclerView.ViewHolder> {
    override fun createView(context: Context, parent: ViewGroup, b: Any?): View {
        return if(b is ViewDataBinding){
            b.setVariable(Constant.parse,this)
            b.setVariable(Constant.vm,this)
            b.executePendingBindings()
            b.root
        }else {
            val binding  = DataBindingUtil.inflate(LayoutInflater.from(context),layoutId(),parent,false) as ViewDataBinding
            binding.setVariable(Constant.parse,this)
            binding.setVariable(Constant.vm,this)
            return binding.root
        }
    }
}