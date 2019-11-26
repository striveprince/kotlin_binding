package com.customers.zktc.base.life

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.customers.zktc.inject.data.Api
import com.lifecycle.binding.inter.Init

abstract class BaseViewModel :ViewModel(), Init<Api> {
    lateinit var api:Api
    override fun initData(api:Api, owner: LifecycleOwner, bundle: Bundle?) {
        this.api = api
    }
}