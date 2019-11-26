package com.customers.zktc.inject.component

import com.customers.zktc.inject.module.ActivityModule
import com.customers.zktc.inject.scope.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent{
    object Config{ const val zktc = "/zktc/" }
//    fun inject(activity: AnkoActivity<out ViewModel>)

    @Subcomponent.Builder
    interface Builder{
        fun applyActivity(activityModule: ActivityModule):Builder
        fun build():ActivityComponent
    }

}