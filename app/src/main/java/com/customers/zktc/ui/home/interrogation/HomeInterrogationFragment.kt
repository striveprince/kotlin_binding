package com.customers.zktc.ui.home.interrogation

import android.content.Context
import com.customers.zktc.base.life.anko.AnkoFragment
import com.customers.zktc.base.util.ankoRecycler
import com.lifecycle.binding.adapter.recycler.RecyclerAdapter
import com.lifecycle.binding.inter.inflate.DiffInflate
import com.lifecycle.binding.inter.inflate.Inflate
import org.jetbrains.anko.AnkoContext

class HomeInterrogationFragment:AnkoFragment<HomeInterrogationModel>() {

    override fun parse(t: HomeInterrogationModel, context: Context): AnkoContext<Context> {
        t.adapter.value  = RecyclerAdapter()
        return ankoRecycler(context, (t.adapter.value as RecyclerAdapter<DiffInflate>),load={ position, state->
            if(!t.loading){
                t.position.value = position
                t.state = state
            }
        })
    }
}