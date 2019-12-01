package com.customers.zktc.base.life.viewmodel

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.customers.zktc.inject.data.Api
import com.lifecycle.binding.inter.LifecycleInit

abstract class BaseViewModel :ViewModel(), LifecycleInit<Api> {
    lateinit var api:Api

    override fun initData(api:Api, owner: LifecycleOwner, bundle: Bundle?) {
        this.api = api
    }

}