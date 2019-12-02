package com.eWord.inquiry.base.life

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
import com.eWord.inquiry.base.life.recycler.RecyclerFragment
import com.eWord.inquiry.base.life.viewmodel.BaseViewModel
import com.eWord.inquiry.inject.component.FragmentComponent
import com.eWord.inquiry.inject.data.Api
import com.eWord.inquiry.inject.module.FragmentModule
import com.eWord.inquiry.ui.TomtawApplication
import com.lifecycle.binding.inter.LifecycleInit
import com.lifecycle.binding.inter.Parse
import javax.inject.Inject
import kotlin.reflect.jvm.javaType
@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<Model:ViewModel,B>:Fragment(),Parse<Model,B>, LifecycleInit<Api> {
    lateinit var model: Model
    @Inject lateinit var api: Api
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView(savedInstanceState)
    }

    fun initView(savedInstanceState:Bundle?)=inject(TomtawApplication.application.fragmentBuilder,savedInstanceState)

    @Suppress("UNCHECKED_CAST")
    fun inject(builder: FragmentComponent.Builder, savedInstanceState: Bundle?):View {
        ARouter.getInstance().inject(this)
        model = initModel()
        val component = builder.applyFragmentModule(FragmentModule(this)).build()
        val method = FragmentComponent::class.java.getDeclaredMethod("inject",this::class.java)
        method.invoke(component,this)
        initData(api,this,savedInstanceState)
        return createView(model,context!!)
    }

    open fun initModel():Model {
        val clazz = javaClass.kotlin.supertypes[0].arguments[0].type!!.javaType as Class<Model>
        return ViewModelProviders.of(this)[clazz]
    }

    @CallSuper
    override fun initData(api: Api, owner: LifecycleOwner, bundle: Bundle?) {
        if(model is LifecycleInit<*>)(model as LifecycleInit<Api>).initData(api,owner,bundle)
    }

    override fun t() = model
}