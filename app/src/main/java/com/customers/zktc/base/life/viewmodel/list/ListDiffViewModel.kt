package com.customers.zktc.base.life.viewmodel.list

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.lifecycle.binding.adapter.recycler.DiffUtilCallback
import com.lifecycle.binding.inter.inflate.DiffInflate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class ListDiffViewModel<Owner : LifecycleOwner, E : DiffInflate> : ListViewModel<Owner, E>() {
    var diff: Job? = null
    override fun refreshList(position: Int, es: List<E>): Boolean {
        diff = CoroutineScope(Dispatchers.Default).launch {
            loading = true
            val result = DiffUtil.calculateDiff(DiffUtilCallback(adapter.value!!.adapterList, es))
            launch(Dispatchers.Main) {
                result.dispatchUpdatesTo(adapter as ListUpdateCallback)
                loading = false
            }
        }
        return true
    }

    override fun onCleared() {
        super.onCleared()
        diff?.cancel()
    }
}