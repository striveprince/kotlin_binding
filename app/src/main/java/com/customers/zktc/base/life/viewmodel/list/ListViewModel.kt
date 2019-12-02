package com.customers.zktc.base.life.viewmodel.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.customers.zktc.base.life.viewmodel.LifeViewModel
import com.customers.zktc.inject.data.Api
import com.lifecycle.binding.adapter.AdapterType
import com.lifecycle.binding.adapter.IEvent
import com.lifecycle.binding.adapter.IListAdapter
import com.lifecycle.binding.adapter.recycler.RecyclerAdapter
import com.lifecycle.binding.inter.inflate.Inflate
import com.lifecycle.binding.util.observer
import io.reactivex.Observable
import io.reactivex.Single

abstract class ListViewModel<Owner : LifecycleOwner, E : Inflate> : LifeViewModel<Owner>(), IListAdapter<E> {
    var state = AdapterType.no
    var position = 0
    val loading  = MutableLiveData<Boolean>(false)
    internal val adapter = MutableLiveData<IListAdapter<E>>(RecyclerAdapter())
    override val adapterList: MutableList<E> = ArrayList()
    override fun attachData(owner: Owner, api: Api, bundle: Bundle?) {
        super.attachData(owner, api, bundle)
        loading.observer(owner) {
            if(it){
            getData(api, position,state)
                .subscribe { es ->
                    setList(position, es, state) } }
        }
        adapter.observer(owner) { state = AdapterType.add }
    }


    abstract fun getData(api: Api, position: Int, state: Int): Single<MutableList<E>>

    override fun notify(p: Int, type: Int, from: Int): Boolean {
        return adapter.value?.notify(p, type, from) ?: false
    }

    override fun notifyList(p: Int, type: Int, es: List<E>, from: Int): Boolean {
        loading.value = false
        return adapter.value?.notifyList(p, type, es, from) ?: false
    }

    override fun notifyDataSetChanged() {
        adapter.value?.notifyDataSetChanged()
    }

    override fun setEvent(position: Int, e: E, type: Int, view: View?): Observable<Any> {
        return adapter.value?.setEvent(position, e, type, view) ?: Observable.just(false as Any)
    }


    override fun onInserted(position: Int, count: Int) {
        adapter.value?.onInserted(position, count)
    }

    override fun onRemoved(position: Int, count: Int) {
        adapter.value?.onRemoved(position, count)
    }

    override fun onMoved(fromPosition: Int, toPosition: Int) {
        adapter.value?.onMoved(fromPosition, toPosition)
    }

    override fun onChanged(position: Int, count: Int, payload: Any?) {
        adapter.value?.onChanged(position, count, payload)
    }

    override fun refreshList(position: Int, es: List<E>): Boolean {
        return adapter.value?.refreshList(position, es)?: false
    }

    override fun setList(position: Int, es: MutableList<E>, type: Int): Boolean {
        return adapter.value?.setList(position, es,type)?: false
    }

    override fun add(position: Int, e: E): Boolean {
        return adapter.value?.add(position, e)?:false
    }

    override fun set(position: Int, e: E): Boolean {
        return adapter.value?.set(position, e)?:false
    }

    override fun remove(position: Int, e: E): Boolean {
        return adapter.value?.remove(position, e)?:false
    }

    override fun move(position: Int, e: E): Boolean {
        return adapter.value?.move(position, e)?:false
    }

    override fun moveList(position: Int, from: Int, size: Int): Boolean {
        return adapter.value?.moveList(position, from, size)?:false
    }

    override fun removeList(position: Int, from: Int, size: Int): Boolean {
        return adapter.value?.removeList(position, from, size)?:false
    }

    override fun addList(position: Int, es: List<E>): Boolean {
        return adapter.value?.addList(position, es)?:false
    }

    override fun addEventAdapter(event: IEvent<E>) {
        adapter.value?.addEventAdapter(event)
    }

    override fun clearData() {
        adapter.value?.clearData()
    }

    override fun size(): Int {
        return adapter.value?.size()?:0
    }

    override fun setIEntity(position: Int, e: E, type: Int, view: View?): Boolean {
        return adapter.value?.setIEntity(position, e, type, view)?:false
    }

    override fun set(position: Int, es: List<E>): Boolean {
        return adapter.value?.set(position, es)?:false
    }
}