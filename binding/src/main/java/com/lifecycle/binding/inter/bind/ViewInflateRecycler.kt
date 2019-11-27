package com.lifecycle.binding.inter.bind

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class ViewInflateRecycler<Bean,Binding:ViewDataBinding>(private val bean:Bean) :BindRecycler<Bean,Binding>{
    override var holder: RecyclerView.ViewHolder?=null
    override fun t(): Bean = bean
}