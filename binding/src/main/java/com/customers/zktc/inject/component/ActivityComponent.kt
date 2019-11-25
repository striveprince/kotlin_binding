package com.customers.zktc.inject.component

import androidx.lifecycle.ViewModel
import com.customers.zktc.base.life.AnkoActivity
import com.customers.zktc.inject.module.ActivityModule
import com.customers.zktc.inject.scope.ActivityScope
import dagger.Component
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