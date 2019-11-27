package com.lifecycle.binding.adapter

import android.view.View

interface IEventAdapter<E> : IListAdapter<E> {
    val adapterList:List<E>
    fun clear(){}
    fun size(): Int = adapterList.size
    fun setIEntity(position: Int, e: E, type: Int, view: View?): Boolean
}