package com.customers.zktc.inject.component

import com.customers.zktc.inject.component.ActivityComponent.Config.tomtaw
import com.customers.zktc.inject.module.FragmentModule
import com.customers.zktc.inject.scope.FragmentScope
import dagger.Subcomponent

@Subcomponent(modules = [FragmentModule::class])
@FragmentScope
interface FragmentComponent {
    object Config{ const val fragment = tomtaw+"fragment/" }

    @Subcomponent.Builder
    interface Builder{
        fun applyFragmentModule(fragmentModule: FragmentModule):Builder
        fun build():FragmentComponent
    }

}