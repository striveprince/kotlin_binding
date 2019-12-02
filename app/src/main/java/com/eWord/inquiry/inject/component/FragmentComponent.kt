package com.eWord.inquiry.inject.component

import com.eWord.inquiry.inject.component.ActivityComponent.Config.tomtaw
import com.eWord.inquiry.inject.module.FragmentModule
import com.eWord.inquiry.inject.scope.FragmentScope
import com.eWord.inquiry.ui.home.browse.HomeBrowseFragment
import com.eWord.inquiry.ui.home.interrogation.HomeInterrogationFragment
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