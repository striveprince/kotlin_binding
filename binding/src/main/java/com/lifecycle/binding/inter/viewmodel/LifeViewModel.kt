package com.lifecycle.binding.inter.viewmodel

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.lifecycle.binding.inter.LifecycleInit
import io.reactivex.internal.disposables.ListCompositeDisposable

@Suppress("UNCHECKED_CAST")
abstract class LifeViewModel<Owner:LifecycleOwner,Api> : ViewModel(),LifecycleInit<Api>{
    val disposables = ListCompositeDisposable()
    override fun initData(api:Api, owner: LifecycleOwner, bundle: Bundle? ) {
        attachData(owner as Owner,api,bundle)
    }

    /**
     * It's not recommended to use it init data
     * @param owner it have activity object,please don't give it to property otherwise,it will leak
     * */
    open fun attachData(owner: Owner, api:Api, bundle: Bundle?){}
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}