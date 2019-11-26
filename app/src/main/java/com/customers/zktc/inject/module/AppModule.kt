package com.customers.zktc.inject.module

import android.content.Context
import android.content.res.Resources
import com.customers.zktc.inject.component.ActivityComponent
import com.customers.zktc.inject.component.FragmentComponent
import com.customers.zktc.inject.qualifier.AppContext
import com.customers.zktc.inject.scope.ApplicationScope
import com.customers.zktc.ui.TomtawApplication
import com.lifecycle.binding.App
import dagger.Module
import dagger.Provides

@Module(subcomponents = [ActivityComponent::class, FragmentComponent::class])
class AppModule(val app:TomtawApplication) {
    init { App(app) }

    @AppContext
    @Provides
    @ApplicationScope
    internal fun getContext():Context{
        return app
    }

    @AppContext
    @Provides
    @ApplicationScope
    internal fun getApplication():TomtawApplication{
        return app
    }

    @AppContext
    @Provides
    @ApplicationScope
    internal fun provideResources():Resources{
        return app.resources
    }


}