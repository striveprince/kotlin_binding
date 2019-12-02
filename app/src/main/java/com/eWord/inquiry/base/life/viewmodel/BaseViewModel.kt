package com.eWord.inquiry.base.life.viewmodel

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import com.eWord.inquiry.inject.data.Api
import com.lifecycle.binding.inter.inflate.DiffInflate
import com.lifecycle.binding.viewmodel.LifeViewModel
import com.lifecycle.binding.viewmodel.list.ListDiffViewModel
import com.lifecycle.binding.viewmodel.list.ListViewModel

abstract class BaseViewModel:LifeViewModel<LifecycleOwner,Api>(){
    lateinit var api:Api
    override fun initData(api: Api, owner: LifecycleOwner, bundle: Bundle?) {
        this.api = api
        super.initData(api, owner, bundle)
    }
}

abstract class BaseDiffListViewModel<E : DiffInflate>:ListDiffViewModel<LifecycleOwner,E,Api>(){
    lateinit var api:Api
    override fun initData(api: Api, owner: LifecycleOwner, bundle: Bundle?) {
        this.api = api
        super.initData(api, owner, bundle)
    }
}

abstract class BaseListViewModel<E : DiffInflate>: ListViewModel<LifecycleOwner, E, Api>(){
    lateinit var api:Api
    override fun initData(api: Api, owner: LifecycleOwner, bundle: Bundle?) {
        this.api = api
        super.initData(api, owner, bundle)
    }
}