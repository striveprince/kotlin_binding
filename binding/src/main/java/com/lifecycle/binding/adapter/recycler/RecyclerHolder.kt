package com.lifecycle.binding.adapter.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lifecycle.binding.R
import com.lifecycle.binding.adapter.IEventAdapter
import com.lifecycle.binding.inter.inflate.Inflate

class RecyclerHolder<E: Inflate<RecyclerView.ViewHolder>>(private val v: ViewGroup, var e:E)
    :RecyclerView.ViewHolder(e.createView(v.context,v,null)){

    fun bindViewHolder(e: E, eventAdapter: IEventAdapter<E>){
        this.e = e
        e.setHolder(this)
        val view = e.createView(v.context!!,v,itemView.getTag(R.id.dataBinding))
        view.setTag(R.id.inflate,e)
    }
}