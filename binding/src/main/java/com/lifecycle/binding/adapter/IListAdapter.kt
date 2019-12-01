package com.lifecycle.binding.adapter

import android.view.View
import androidx.recyclerview.widget.ListUpdateCallback


interface IListAdapter<E> :IEvent<E>, ListUpdateCallback {

    val adapterList: MutableList<E>

    fun addEventAdapter(event: IEvent<E>){}

    fun clear() {
        adapterList.clear()
        notifyList(0,AdapterType.remove,adapterList)
    }

    fun size(): Int = adapterList.size
    fun setIEntity(position: Int, e: E, @AdapterEvent type: Int, view: View?): Boolean {
        return when (type) {
            AdapterType.add -> add(position, e)
            AdapterType.set -> set(position, e)
            AdapterType.remove -> remove(position, e)
            AdapterType.move -> move(position, e)
            else -> false
        }
    }

    fun setList(position: Int, es: MutableList<E>, type: Int): Boolean {
        if (es.isEmpty()) return false
        return when (type) {
            AdapterType.add -> addList(position, es)
            AdapterType.move -> moveList(position, adapterList.indexOf(es.first()), es.size)
            AdapterType.remove -> removeList(position, adapterList.indexOf(es.first()), es.size)
            AdapterType.refresh -> refreshList(position, es)
            AdapterType.set -> set(position,es)
            else -> false
        }
    }

    fun add(position: Int = -1, e: E): Boolean {
        val p = if (position in adapterList.indices) {
            adapterList.add(position, e)
            position
        } else {
            adapterList.add(e)
            adapterList.lastIndex
        }
        return notify(p, AdapterType.add)
    }

    fun set(position: Int, e: E): Boolean {
        return if (position in adapterList.indices) {
            adapterList[position] = e
            notify(position, AdapterType.set)
        } else false
    }

    fun move(position: Int, e: E): Boolean {
        val p = if (position in adapterList.indices) position else adapterList.lastIndex
        val from = adapterList.indexOf(e)
        return if (from >= 0 && from != p && adapterList.remove(e)) {
            adapterList.add(p, e)
            notify(p, AdapterType.move, from)
        } else false
    }

    fun remove(position: Int, e: E): Boolean {
        return if (position in adapterList.indices) {
            adapterList.remove(e)
            notify(position, AdapterType.remove)
        } else false
    }

    fun addList(position: Int = Int.MAX_VALUE, es: List<E>): Boolean {
        val p = if (position in adapterList.indices) position else adapterList.size
        adapterList.addAll(p, es)
        return notifyList(position, AdapterType.add, es)
    }

    fun moveList(position: Int, from: Int, size: Int): Boolean {
        if (from < 0) return false
        val list = ArrayList(adapterList.subList(from, size))
        adapterList.removeAll(list)
        val p = if (position in adapterList.indices) position else adapterList.size
        adapterList.addAll(p, list)
        return notifyList(p, AdapterType.move, list)
    }

    fun removeList(position: Int, from: Int, size: Int): Boolean {
        if (from < 0) return false
        val list = ArrayList(adapterList.subList(from, size))
        adapterList.removeAll(list)
        val p = if (position in adapterList.indices) position else adapterList.size
        return notifyList(p, AdapterType.remove, list)
    }

    fun refreshList(position: Int, es: List<E>): Boolean {
        return if (position in adapterList.indices) {
            val list = ArrayList(adapterList.subList(0, position))
            list.addAll(position, es)
            notifyList(position, AdapterType.refresh, es)
        } else {
            addList(position, es)
        }
    }

    fun set(position: Int,es:List<E>):Boolean{
        if(position in adapterList.indices&&(position+es.size in adapterList.indices)){
            for (index in es.indices) {
                adapterList[index+position] = es[index]
            }
            return true
        }
        return false
    }

    fun notify(p: Int, type: Int, from: Int = 0): Boolean

    fun notifyList(p: Int, type: Int, es: List<E>, from: Int = 0): Boolean

    fun notifyDataSetChanged()


    override fun onChanged(position: Int, count: Int, payload: Any?) {

    }

    override fun onMoved(fromPosition: Int, toPosition: Int) {

    }

    override fun onInserted(position: Int, count: Int) {

    }

    override fun onRemoved(position: Int, count: Int) {
    }
}
