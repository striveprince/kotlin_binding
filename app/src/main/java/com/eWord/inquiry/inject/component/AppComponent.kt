package com.eWord.inquiry.inject.component

import android.content.Context
import com.eWord.inquiry.inject.data.Api
import com.eWord.inquiry.inject.module.AppModule
import com.eWord.inquiry.inject.module.DataModule
import com.eWord.inquiry.inject.qualifier.AppContext
import com.eWord.inquiry.inject.scope.ApplicationScope
import com.eWord.inquiry.ui.TomtawApplication
import dagger.Component

@Component(modules = [AppModule::class,DataModule::class])
@ApplicationScope
interface AppComponent {
    fun inject(application: TomtawApplication)
    @AppContext fun getContext():Context
    fun getApi(): Api

    fun buildActivityComponent():ActivityComponent.Builder
    fun buildFragmentComponent():FragmentComponent.Builder
}