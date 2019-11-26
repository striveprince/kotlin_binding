package com.customers.zktc.base.life

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.launcher.ARouter
import com.customers.zktc.inject.component.ActivityComponent
import com.customers.zktc.inject.data.Api
import com.customers.zktc.inject.module.ActivityModule
import com.customers.zktc.ui.ARouterUtil
import com.customers.zktc.ui.TomtawApplication
import com.lifecycle.binding.inter.Init
import com.lifecycle.binding.inter.Parse
import javax.inject.Inject
import kotlin.reflect.jvm.javaType

abstract class BaseActivity<Model : ViewModel> : AppCompatActivity(), Parse<Model>, Init<Api> {
    lateinit var model: Model
    @Inject lateinit var api: Api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(inject(TomtawApplication.activityBuilder, savedInstanceState))
    }

    @Suppress("UNCHECKED_CAST")
    open fun inject(builder: ActivityComponent.Builder?, savedInstanceState: Bundle?): View {
        ARouter.getInstance().inject(this)
        model = ViewModelProviders.of(this)[javaClass.kotlin.supertypes[0].arguments[0].type!!.javaType as Class<Model>]
        val component = builder!!.applyActivity(ActivityModule(this)).build()
        val method = ActivityComponent::class.java.getDeclaredMethod("inject", this::class.java)
        method.invoke(component, this)
        val view = createView(model, this)
        initData(api, this, savedInstanceState)
        return view
    }

    @CallSuper
    override fun initData(api: Api, owner: LifecycleOwner, bundle: Bundle?) {
        if (model is BaseViewModel) (model as BaseViewModel).initData(api, owner)
    }
}

