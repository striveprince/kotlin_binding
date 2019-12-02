package com.eWord.inquiry.inject.component

import com.eWord.inquiry.inject.module.ActivityModule
import com.eWord.inquiry.inject.scope.ActivityScope
import com.eWord.inquiry.ui.home.HomeActivity
import com.eWord.inquiry.ui.start.StartActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent{
    object Config{ const val tomtaw = "/tomtaw/" }
//    fun inject(activity: AnkoActivity<out ViewModel>)
    fun inject(activity: StartActivity)
    fun inject(activity: HomeActivity)

    @Subcomponent.Builder
    interface Builder{
        fun applyActivity(activityModule: ActivityModule):Builder
        fun build():ActivityComponent
    }

}