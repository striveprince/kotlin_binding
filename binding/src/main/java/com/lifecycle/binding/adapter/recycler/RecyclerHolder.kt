package com.lifecycle.binding.adapter.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lifecycle.binding.R
import com.lifecycle.binding.adapter.IEvent
import com.lifecycle.binding.inter.inflate.Inflate
import com.lifecycle.binding.inter.inflate.Recycler

class RecyclerHolder<E: Inflate>(private val v: ViewGroup, var e:E)
    :RecyclerView.ViewHolder(e.createView(v.context,v,null)){

    fun bindViewHolder(e: E, event: IEvent<E>){
        this.e = e
        e.event(event)
        if(e is Recycler) e.holder(this)
        e.createView(v.context!!,v,itemView)

    }
}