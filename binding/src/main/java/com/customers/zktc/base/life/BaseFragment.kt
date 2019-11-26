package com.customers.zktc.base.life

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.customers.zktc.inject.component.FragmentComponent
import com.customers.zktc.inject.data.Api
import com.customers.zktc.inject.module.FragmentModule
import com.customers.zktc.ui.DemoApplication
import com.lifecycle.binding.inter.Parse
import javax.inject.Inject
import kotlin.reflect.jvm.javaType

abstract class BaseFragment<Model:ViewModel>():Fragment(),Parse<Model> {
    lateinit var model: Model
    @Inject lateinit var api: Api
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inject(DemoApplication.application.fragmentBuilder,savedInstanceState)
        val view = createView(model,context!!)
        initData(api,this,savedInstanceState)
        return view
    }

    @Suppress("UNCHECKED_CAST")
    fun inject(builder: FragmentComponent.Builder, savedInstanceState: Bundle?) {
        model = ViewModelProviders.of(this)[javaClass.kotlin.supertypes[0].arguments[0].type!!.javaType as Class<Model>]
        val component = builder.applyFragmentModule(FragmentModule(this)).build()
        val method = FragmentComponent::class.java.getDeclaredMethod("inject",this::class.java)
        method.invoke(component,this)
    }

    override fun initData(api: Api, owner: LifecycleOwner, bundle: Bundle?) {
        if(model is BaseViewModel<*>)(model as BaseViewModel<*>).initData(api,owner)
    }
}