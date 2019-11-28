package com.lifecycle.binding.adapter.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lifecycle.binding.R
import com.lifecycle.binding.adapter.IEventAdapter
import com.lifecycle.binding.inter.bind.BindRecycler
import com.lifecycle.binding.inter.inflate.Inflate
import com.lifecycle.binding.inter.inflate.Recycler
import com.lifecycle.binding.inter.inflate.RecyclerInflate

class RecyclerHolder<E: Inflate>(private val v: ViewGroup, var e:E)
    :RecyclerView.ViewHolder(e.createView(v.context,v,null)){

    fun bindViewHolder(e: E, eventAdapter: IEventAdapter<E>){
        this.e = e
        if(e is Recycler) e.holder(this)
        val view = e.createView(v.context!!,v,itemView.getTag(R.id.dataBinding))
        view.setTag(R.id.inflate,e)
    }
}