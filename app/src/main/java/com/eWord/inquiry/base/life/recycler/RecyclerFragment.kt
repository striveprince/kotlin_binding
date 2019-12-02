package com.eWord.inquiry.base.life.recycler

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import com.eWord.inquiry.base.life.anko.AnkoFragment
import com.eWord.inquiry.base.util.recycler
import com.eWord.inquiry.inject.data.Api
import com.lifecycle.binding.inter.inflate.DiffInflate
import io.reactivex.Single
import org.jetbrains.anko.AnkoContext
@Suppress("UNCHECKED_CAST")
abstract class RecyclerFragment<E:DiffInflate>:AnkoFragment<RecyclerModel<E>>() {
    override fun initData(api: Api, owner: LifecycleOwner, bundle: Bundle?) {
        model.block = {_, position, state ->  getData(position, state)}
        super.initData(api, owner, bundle)
    }

    abstract fun getData(position:Int,state:Int):Single<MutableList<E>>


    override fun parse(t: RecyclerModel<E>, context: Context): AnkoContext<Context> {
        return recycler(context,t)
    }

    override fun initModel(): RecyclerModel<E> {
        return ViewModelProviders.of(this)[RecyclerModel::class.java] as RecyclerModel<E>
    }
}