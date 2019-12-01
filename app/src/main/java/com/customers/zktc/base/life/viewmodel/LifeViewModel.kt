package com.customers.zktc.base.life.viewmodel

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import com.customers.zktc.inject.data.Api

@Suppress("UNCHECKED_CAST")
abstract class LifeViewModel<Owner:LifecycleOwner> : BaseViewModel(){
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