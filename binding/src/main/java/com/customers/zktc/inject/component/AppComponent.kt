package com.customers.zktc.inject.component

import android.content.Context
import com.customers.zktc.inject.data.Api
import com.customers.zktc.inject.module.AppModule
import com.customers.zktc.inject.module.DataModule
import com.customers.zktc.inject.qualifier.AppContext
import com.customers.zktc.inject.scope.ApplicationScope
import com.customers.zktc.ui.DemoApplication
import dagger.Component

@Component(modules = [AppModule::class,DataModule::class])
@ApplicationScope
interface AppComponent {
    fun inject(application: DemoApplication)
    @AppContext fun getContext():Context
    fun getApi(): Api

    fun buildActivityComponent():ActivityComponent.Builder
    fun buildFragmentComponent():FragmentComponent.Builder
}