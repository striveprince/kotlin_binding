package com.customers.zktc.base.life

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.customers.zktc.inject.data.Api
import com.lifecycle.binding.inter.Init

@Suppress("UNCHECKED_CAST")
abstract class LifeViewModel<Owner:LifecycleOwner> :BaseViewModel(){
    override fun initData(api:Api, owner: LifecycleOwner, bundle: Bundle? ) {
        super.initData(api, owner, bundle)
        attachData(owner as Owner,api,bundle)
    }

    /**
     * It's not recommended to use it init data
     * @param owner it have activity object,please don't give it to property otherwise,it will leak
     * */
    open fun attachData(owner: Owner, api:Api, bundle: Bundle?){}
}