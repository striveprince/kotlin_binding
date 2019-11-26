package com.customers.zktc.base.life.binding

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.customers.zktc.base.life.BaseActivity
import com.customers.zktc.inject.data.Api
import com.lifecycle.binding.inter.bind.BindInit

abstract class DataBindingActivity<Model:ViewModel,Binding :ViewDataBinding> :
    BaseActivity<Model>(), BindInit<Model,Binding,Api> {
    lateinit var binding:Binding
    override fun parse(t: Model, context: Context, parent: ViewGroup?, attachToParent: Boolean): Binding {
        binding= super.parse(t, context, parent, attachToParent)
        binding.lifecycleOwner = this
        return binding
    }
}