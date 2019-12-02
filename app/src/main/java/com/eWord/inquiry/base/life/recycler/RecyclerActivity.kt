package com.eWord.inquiry.base.life.recycler

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import com.eWord.inquiry.base.life.anko.AnkoActivity
import com.eWord.inquiry.base.util.recycler
import com.eWord.inquiry.inject.data.Api
import com.lifecycle.binding.inter.inflate.DiffInflate
import io.reactivex.Single
import org.jetbrains.anko.AnkoContext
import kotlin.reflect.jvm.javaType

@Suppress("UNCHECKED_CAST")
abstract class RecyclerActivity<E:DiffInflate>:AnkoActivity<RecyclerModel<E>>() {
    override fun initData(api: Api, owner: LifecycleOwner, bundle: Bundle?) {
        model.block = {_, position, state ->  getData(position, state)}
        super.initData(api, owner, bundle)
    }

    abstract fun getData(position:Int,state:Int):Single<MutableList<E>>

    override fun parse(t: RecyclerModel<E>, context: Context): AnkoContext<Context> {
        return recycler(context,t)
    }


    override fun initModel(): RecyclerModel<E> {
//        val clazz = javaClass.kotlin.supertypes[0].arguments[0].type!!.javaType as Class<E>
        return ViewModelProviders.of(this)[RecyclerModel::class.java] as RecyclerModel<E>
    }
}