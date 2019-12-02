package com.customers.zktc.ui.home.interrogation

import android.content.Context
import com.customers.zktc.base.life.anko.AnkoFragment
import com.customers.zktc.base.util.recycler
import com.lifecycle.binding.adapter.recycler.RecyclerAdapter
import com.lifecycle.binding.inter.inflate.DiffInflate
import org.jetbrains.anko.AnkoContext

class HomeInterrogationFragment:AnkoFragment<HomeInterrogationModel>() {

    override fun parse(t: HomeInterrogationModel, context: Context): AnkoContext<Context> {
        return recycler(context,t)
    }
}