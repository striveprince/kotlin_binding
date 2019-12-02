package com.customers.zktc.base.life.viewmodel

import androidx.lifecycle.LifecycleOwner
import com.customers.zktc.inject.data.Api
import com.lifecycle.binding.inter.inflate.DiffInflate
import com.lifecycle.binding.inter.viewmodel.LifeViewModel
import com.lifecycle.binding.inter.viewmodel.list.ListDiffViewModel
import com.lifecycle.binding.inter.viewmodel.list.ListViewModel

abstract class BaseViewModel<Owner: LifecycleOwner>:LifeViewModel<Owner,Api>()

abstract class BaseDiffListViewModel<Owner : LifecycleOwner, E : DiffInflate>:ListDiffViewModel<Owner,E,Api>()

abstract class BaseListViewModel<Owner : LifecycleOwner, E : DiffInflate>: ListViewModel<Owner, E, Api>()