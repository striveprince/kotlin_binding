package com.customers.zktc.base.life

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.launcher.ARouter
import com.customers.zktc.base.life.viewmodel.BaseViewModel
import com.customers.zktc.inject.component.FragmentComponent
import com.customers.zktc.inject.data.Api
import com.customers.zktc.inject.module.FragmentModule
import com.customers.zktc.ui.TomtawApplication
import com.lifecycle.binding.inter.LifecycleInit
import com.lifecycle.binding.inter.Parse
import javax.inject.Inject
import kotlin.reflect.jvm.javaType

abstract class BaseFragment<Model:ViewModel,B>:Fragment(),Parse<Model,B>, LifecycleInit<Api> {
    lateinit var model: Model
    @Inject lateinit var api: Api
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inject(TomtawApplication.application.fragmentBuilder,savedInstanceState)
        return createView(model,context!!)
    }

    @Suppress("UNCHECKED_CAST")
    fun inject(builder: FragmentComponent.Builder, savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        model = ViewModelProviders.of(this)[javaClass.kotlin.supertypes[0].arguments[0].type!!.javaType as Class<Model>]
        val component = builder.applyFragmentModule(FragmentModule(this)).build()
        val method = FragmentComponent::class.java.getDeclaredMethod("inject",this::class.java)
        method.invoke(component,this)
        initData(api,this,savedInstanceState)
    }

    @CallSuper
    override fun initData(api: Api, owner: LifecycleOwner, bundle: Bundle?) {
        if(model is BaseViewModel)(model as BaseViewModel).initData(api,owner,bundle)
    }

    override fun t() = model
}