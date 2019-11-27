package com.customers.zktc.ui.home.message

import android.content.Context
import com.customers.zktc.base.life.anko.AnkoFragment
import com.lifecycle.binding.util.recyclerView
import org.jetbrains.anko.AnkoContext

class HomeMessageFragment :AnkoFragment<HomeMessageModel>(){
    override fun parse(t: HomeMessageModel, context: Context): AnkoContext<Context> {
        return AnkoContext.Companion.create(context).apply {
            recyclerView {  }
        }
    }
}