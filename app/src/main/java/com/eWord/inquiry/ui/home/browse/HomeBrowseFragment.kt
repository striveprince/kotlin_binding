package com.eWord.inquiry.ui.home.browse

import android.content.Context
import com.eWord.inquiry.base.life.anko.AnkoFragment
import org.jetbrains.anko.AnkoContext

class HomeBrowseFragment :AnkoFragment<HomeBrowseModel>(){
    override fun parse(t: HomeBrowseModel, context: Context): AnkoContext<Context> {
        return AnkoContext.Companion.create(context).apply {  }
    }
}