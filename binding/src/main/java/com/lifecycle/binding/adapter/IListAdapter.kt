package com.lifecycle.binding.adapter


interface IListAdapter<E>{
    fun setList(position: Int, es: List<E>,  type: Int): Boolean
}
