package com.customers.zktc.inject.component

import com.customers.zktc.inject.component.ActivityComponent.Config.tomtaw
import com.customers.zktc.inject.module.FragmentModule
import com.customers.zktc.inject.scope.FragmentScope
import com.customers.zktc.ui.home.browse.HomeBrowseFragment
import com.customers.zktc.ui.home.interrogation.HomeInterrogationFragment
import dagger.Subcomponent

@Subcomponent(modules = [FragmentModule::class])
@FragmentScope
interface FragmentComponent {
    object Config {
        const val fragment = tomtaw + "fragment/"
    }

    fun inject(homePageFragment: HomeInterrogationFragment)
    fun inject(homePageFragment: HomeBrowseFragment)
    @Subcomponent.Builder
    interface Builder {
        fun applyFragmentModule(fragmentModule: FragmentModule): Builder
        fun build(): FragmentComponent
    }

}