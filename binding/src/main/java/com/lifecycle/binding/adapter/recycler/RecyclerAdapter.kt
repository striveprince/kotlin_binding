package com.lifecycle.binding.adapter.recycler

import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lifecycle.binding.adapter.IEventAdapter
import com.lifecycle.binding.inter.Inflate

class RecyclerAdapter<E: Inflate<RecyclerView.ViewHolder>>:RecyclerView.Adapter<RecyclerHolder<E>>(),IEventAdapter<E> {
    override val adapterList = ArrayList<E>()
    private val sparseArray = SparseArray<E>()
    private val eventAdapter:IEventAdapter<E> by lazy { this }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder<E> {
        return RecyclerHolder(parent,sparseArray.get(viewType))
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    override fun getItemViewType(position: Int): Int {
        val e = adapterList[position]
        val viewType = e.getLayoutId()
        sparseArray.put(viewType, e)
        return viewType
    }

    override fun onBindViewHolder(holder: RecyclerHolder<E>, position: Int) {
        holder.bindViewHolder(adapterList[position],eventAdapter)
    }


    override fun setIEntity(position: Int, e: E, type: Int, view: View?): Boolean {
        return false
    }

    override fun setList(position: Int, es: List<E>, type: Int): Boolean {
        return false
    }
}