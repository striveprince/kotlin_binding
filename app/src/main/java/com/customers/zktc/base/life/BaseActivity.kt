package com.customers.zktc.base.life

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.launcher.ARouter
import com.customers.zktc.R
import com.customers.zktc.base.life.viewmodel.BaseViewModel
import com.customers.zktc.inject.component.ActivityComponent
import com.customers.zktc.inject.data.Api
import com.customers.zktc.inject.module.ActivityModule
import com.customers.zktc.ui.TomtawApplication
import com.lifecycle.binding.base.view.SwipeBackLayout
import com.lifecycle.binding.inter.LifecycleInit
import com.lifecycle.binding.inter.Parse
import javax.inject.Inject
import kotlin.reflect.jvm.javaType

abstract class BaseActivity<Model : ViewModel,B> : AppCompatActivity(), Parse<Model,B>, LifecycleInit<Api> {
    lateinit var model: Model
    @Inject lateinit var api: Api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initView(savedInstanceState))
    }

    open fun initView(savedInstanceState: Bundle?):View{
        val v=inject(TomtawApplication.activityBuilder, savedInstanceState)
        return if(isSwipe()!=SwipeBackLayout.FROM_NO){
            val view = LayoutInflater.from(this).inflate(R.layout.activity_base,null)
            val swipeBackLayout: SwipeBackLayout = view.findViewById(R.id.swipe_back_layout)
            swipeBackLayout.directionMode = isSwipe()
            swipeBackLayout.addView(v, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
            val imageView: ImageView = view.findViewById(R.id.iv_shadow)
            swipeBackLayout.setOnSwipeBackListener { _, f -> imageView.alpha = 1 - f }
            view
        }else v
    }

    open fun isSwipe(): Int = SwipeBackLayout.FROM_LEFT


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

    override fun t()=model

    @CallSuper
    override fun initData(api: Api, owner: LifecycleOwner, bundle: Bundle?) {
        if (model is BaseViewModel) (model as BaseViewModel).initData(api, owner,bundle)
    }



}

