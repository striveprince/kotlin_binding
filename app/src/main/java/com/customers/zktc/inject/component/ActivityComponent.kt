package com.customers.zktc.inject.component

import com.customers.zktc.inject.module.ActivityModule
import com.customers.zktc.inject.scope.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent{
    object Config{ const val tomtaw = "/tomtaw/" }
//    fun inject(activity: AnkoActivity<out ViewModel>)

    @Subcomponent.Builder
    interface Builder{
        fun applyActivity(activityModule: ActivityModule):Builder
        fun build():ActivityComponent
    }

}