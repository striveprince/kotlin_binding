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

abstract class ListViewModel<Owner : LifecycleOwner, E : Inflate>(val adapter: IListAdapter<E> =RecyclerAdapter()) : LifeViewModel<Owner>(), IListAdapter<E> {
    var state = AdapterType.no
    var position = 0
    val loading  = MutableLiveData<Boolean>(false)
    override val adapterList: MutableList<E> = ArrayList()
    override fun attachData(owner: Owner, api: Api, bundle: Bundle?) {
        super.attachData(owner, api, bundle)
        loading.observer(owner) {
            if(it){
            getData(api, position,state)
                .subscribe { es ->
                    setList(position, es, state) } }
        }
    }


    abstract fun getData(api: Api, position: Int, state: Int): Single<MutableList<E>>

    override fun notify(p: Int, type: Int, from: Int): Boolean {
        return adapter.notify(p, type, from)
    }

    override fun notifyList(p: Int, type: Int, es: List<E>, from: Int): Boolean {
        loading.value = false
        return adapter.notifyList(p, type, es, from)
    }

    override fun notifyDataSetChanged() {
        adapter.notifyDataSetChanged()
    }

    override fun setEvent(position: Int, e: E, type: Int, view: View?): Observable<Any> {
        return adapter.setEvent(position, e, type, view)
    }


    override fun onInserted(position: Int, count: Int) {
        adapter.onInserted(position, count)
    }

    override fun onRemoved(position: Int, count: Int) {
        adapter.onRemoved(position, count)
    }

    override fun onMoved(fromPosition: Int, toPosition: Int) {
        adapter.onMoved(fromPosition, toPosition)
    }

    override fun onChanged(position: Int, count: Int, payload: Any?) {
        adapter.onChanged(position, count, payload)
    }

    override fun refreshList(position: Int, es: List<E>): Boolean {
        return adapter.refreshList(position, es)
    }

    override fun setList(position: Int, es: MutableList<E>, type: Int): Boolean {
        return adapter.setList(position, es,type)
    }

    override fun add(position: Int, e: E): Boolean {
        return adapter.add(position, e)
    }

    override fun set(position: Int, e: E): Boolean {
        return adapter.set(position, e)
    }

    override fun remove(position: Int, e: E): Boolean {
        return adapter.remove(position, e)
    }

    override fun move(position: Int, e: E): Boolean {
        return adapter.move(position, e)
    }

    override fun moveList(position: Int, from: Int, size: Int): Boolean {
        return adapter.moveList(position, from, size)
    }

    override fun removeList(position: Int, from: Int, size: Int): Boolean {
        return adapter.removeList(position, from, size)
    }

    override fun addList(position: Int, es: List<E>): Boolean {
        return adapter.addList(position, es)
    }

    override fun addEventAdapter(event: IEvent<E>) {
        adapter.addEventAdapter(event)
    }

    override fun clearList() {
        adapter.clearList()
    }

    override fun size(): Int {
        return adapter.size()
    }

    override fun setIEntity(position: Int, e: E, type: Int, view: View?): Boolean {
        return adapter.setIEntity(position, e, type, view)
    }

    override fun set(position: Int, es: List<E>): Boolean {
        return adapter.set(position, es)
    }
}