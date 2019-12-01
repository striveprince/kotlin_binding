package com.customers.zktc.base.util

import android.content.Context
import android.view.ViewManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lifecycle.binding.adapter.AdapterType
import com.lifecycle.binding.adapter.recycler.RecyclerAdapter
import com.lifecycle.binding.inter.inflate.Inflate
import com.lifecycle.binding.util.recyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.simple.SimpleMultiListener
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.smartRefreshLayout(init: SmartRefreshLayout.() -> Unit): SmartRefreshLayout =
    ankoView({ SmartRefreshLayout(it) }, theme = 0, init = init)

fun ankoRecycler(
    context: Context,
    recyclerAdapter: RecyclerView.Adapter<*> = RecyclerAdapter<Inflate>(),
    recyclerManager: RecyclerView.LayoutManager = LinearLayoutManager(context),
    recyclerAnimator: RecyclerView.ItemAnimator? = DefaultItemAnimator(),
    load: (Int, Int) -> Unit= {_,_-> }
) =
    AnkoContext.create(context).apply {
        smartRefreshLayout {
            setOnMultiListener(object : SimpleMultiListener() {
                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    super.onLoadMore(refreshLayout)
                    if (recyclerManager is LinearLayoutManager) {
                        val position = recyclerManager.findLastVisibleItemPosition()
                        load(position, AdapterType.add)
                    }
                }

                override fun onRefresh(refreshLayout: RefreshLayout) {
                    super.onRefresh(refreshLayout)
                    load(0, AdapterType.refresh)
                }
            })
            recyclerView {
                adapter = recyclerAdapter
                layoutManager = recyclerManager
                itemAnimator = recyclerAnimator
            }
        }
    }
