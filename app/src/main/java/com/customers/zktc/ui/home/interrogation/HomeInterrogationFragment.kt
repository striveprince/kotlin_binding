package com.customers.zktc.ui.home.interrogation

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.customers.zktc.base.life.BaseFragment
import com.customers.zktc.base.life.anko.AnkoActivity
import com.customers.zktc.base.life.anko.AnkoFragment
import com.lifecycle.binding.adapter.recycler.RecyclerAdapter
import com.lifecycle.binding.inter.Parse
import com.lifecycle.binding.util.recyclerView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.frameLayout

class HomeInterrogationFragment:AnkoFragment<HomeInterrogationModel>() {

    override fun parse(t: HomeInterrogationModel, context: Context): AnkoContext<Context> {

//        val recyclerAdapter = RecyclerAdapter<Parse<Any>>()
        return AnkoContext.Companion.create(context).apply {
            frameLayout()
//            recyclerView{
//                adapter = recyclerAdapter
//                layoutManager = LinearLayoutManager(context)
//            }
        }
    }
}