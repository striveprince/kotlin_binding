package com.customers.zktc.inject.module

import android.content.Context
import android.content.res.Resources
import com.customers.zktc.inject.component.ActivityComponent
import com.customers.zktc.inject.component.FragmentComponent
import com.customers.zktc.inject.qualifier.AppContext
import com.customers.zktc.inject.scope.ApplicationScope
import com.customers.zktc.ui.DemoApplication
import dagger.Module
import dagger.Provides

@Module(subcomponents = [ActivityComponent::class, FragmentComponent::class])
class AppModule(val app:DemoApplication) {

    @AppContext
    @Provides
    @ApplicationScope
    internal fun getContext():Context{
        return app
    }

    @AppContext
    @Provides
    @ApplicationScope
    internal fun getApplication():DemoApplication{
        return app
    }

    @AppContext
    @Provides
    @ApplicationScope
    internal fun provideResources():Resources{
        return app.resources
    }


}