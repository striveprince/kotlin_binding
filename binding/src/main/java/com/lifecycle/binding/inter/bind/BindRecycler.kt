package com.lifecycle.binding.inter.bind

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.lifecycle.binding.Constant
import com.lifecycle.binding.inter.Inflate
import com.lifecycle.binding.util.findLayoutView

/**
 * Company:
 * Description:
 * Author: created by ArvinWang on 2019/11/15 10:28
 * Email: 1033144294@qq.com
 */
interface BindRecycler<T, Binding : ViewDataBinding> : BindParse<T, Binding>, Inflate<RecyclerView.ViewHolder> {

    override fun createView(context: Context, v: ViewGroup, binding: Any?) =
        if(binding is ViewDataBinding){
            binding.setVariable(Constant.inflate,this)
            binding.setVariable(Constant.vm,t())
            binding.executePendingBindings()
            binding.root
        }else createView(t(),context,v,false)

    override fun layoutId()= findLayoutView(javaClass).layout[layoutIndex()]
}