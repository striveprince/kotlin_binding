package com.lifecycle.binding.inter

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import com.customers.zktc.inject.data.Api

interface Init {
    fun initData(api: Api, owner: LifecycleOwner, bundle: Bundle?=null)
}