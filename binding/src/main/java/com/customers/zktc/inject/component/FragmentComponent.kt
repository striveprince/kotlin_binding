package com.customers.zktc.inject.component

import com.customers.zktc.inject.component.ActivityComponent.Config.zktc
import com.customers.zktc.inject.module.FragmentModule
import com.customers.zktc.inject.scope.FragmentScope

import dagger.Component
import dagger.Subcomponent

@Subcomponent(modules = [FragmentModule::class])
@FragmentScope
interface FragmentComponent {
    object Config{ const val fragment = zktc+"fragment/" }

    @Subcomponent.Builder
    interface Builder{
        fun applyFragmentModule(fragmentModule: FragmentModule):Builder
        fun build():FragmentComponent
    }

}