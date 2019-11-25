package com.customers.zktc.base.life

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.customers.zktc.inject.component.ActivityComponent
import com.lifecycle.binding.inter.anko.AnkoParse
import org.jetbrains.anko.AnkoContext
import javax.inject.Inject
import kotlin.reflect.jvm.javaType

/**
 * Company:
 * Description:
 * Author: created by ArvinWang on 2019/11/15 11:40
 * Email: 1033144294@qq.com
 */
abstract class AnkoActivity<Model : ViewModel> : AppCompatActivity(), AnkoParse<Model, AnkoContext<Context>> {
    @Inject lateinit var builder: ActivityComponent.Builder
    lateinit var model: Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNCHECKED_CAST")
        val type = javaClass.kotlin.supertypes[0].arguments[0].type!!.javaType as Class<Model>
        model = ViewModelProviders.of(this)[type]
//        model.setApi
    }

}