package com.customers.zktc.base.life.anko

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.customers.zktc.base.life.BaseFragment
import com.customers.zktc.inject.data.Api
import com.lifecycle.binding.inter.Init
import com.lifecycle.binding.inter.anko.AnkoParse
import org.jetbrains.anko.AnkoContext

/**
 * Company:
 * Description:
 * Author: created by ArvinWang on 2019/11/15 11:41
 * Email: 1033144294@qq.com
 */
abstract class AnkoFragment<Model:ViewModel> :BaseFragment<Model>(),
    AnkoParse<Model,AnkoContext<Context>>