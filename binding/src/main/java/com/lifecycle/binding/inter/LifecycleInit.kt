package com.lifecycle.binding.inter

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner

interface LifecycleInit<Api> {
    fun initData(api: Api, owner: LifecycleOwner, bundle: Bundle?=null)
}