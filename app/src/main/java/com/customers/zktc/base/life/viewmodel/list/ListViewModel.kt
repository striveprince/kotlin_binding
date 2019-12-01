package com.customers.zktc.base.life.viewmodel.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.customers.zktc.base.life.viewmodel.LifeViewModel
import com.customers.zktc.inject.data.Api
import com.lifecycle.binding.adapter.AdapterType
import com.lifecycle.binding.adapter.IListAdapter
import com.lifecycle.binding.inter.inflate.Inflate
import com.lifecycle.binding.util.observer
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.internal.disposables.ListCompositeDisposable

abstract class ListViewModel<Owner : LifecycleOwner, E : Inflate> : LifeViewModel<Owner>(), IListAdapter<E> {
    var state = AdapterType.no
    var loading = false
    var position = MutableLiveData(0)
    internal val adapter = MutableLiveData<IListAdapter<E>>()
    override val adapterList: MutableList<E> = ArrayList()
    private val disposables = ListCompositeDisposable()
    override fun attachData(owner: Owner, api: Api, bundle: Bundle?) {
        super.attachData(owner, api, bundle)
        position.observer(owner) {
            loading = true
            getData(api, state, it)
                .subscribe { es ->
                    loading = false
                    setList(it, es, state)
                }
        }
        adapter.observer(owner) { state = AdapterType.add }
    }


    abstract fun getData(api: Api, state: Int, position: Int): Single<MutableList<E>>

    override fun notify(p: Int, type: Int, from: Int): Boolean {
        return adapter.value?.notify(p, type, from) ?: false
    }

    override fun notifyList(p: Int, type: Int, es: List<E>, from: Int): Boolean {
        return adapter.value?.notifyList(p, type, es, from) ?: false
    }

    override fun notifyDataSetChanged() {
        adapter.value?.notifyDataSetChanged()
    }

    override fun setEvent(position: Int, e: E, type: Int, view: View?): Observable<Any> {
        return adapter.value?.setEvent(position, e, type, view) ?: Observable.just(false as Any)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}