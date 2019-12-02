package com.eWord.inquiry.inject.module

import android.content.Context
import android.content.res.Resources
import com.alibaba.android.arouter.launcher.ARouter
import com.eWord.inquiry.BuildConfig
import com.eWord.inquiry.BR
import com.eWord.inquiry.inject.component.ActivityComponent
import com.eWord.inquiry.inject.component.FragmentComponent
import com.eWord.inquiry.inject.qualifier.AppContext
import com.eWord.inquiry.inject.scope.ApplicationScope
import com.eWord.inquiry.ui.TomtawApplication
import com.lifecycle.binding.App
import com.lifecycle.binding.Constant
import dagger.Module
import dagger.Provides
import timber.log.Timber

@Module(subcomponents = [ActivityComponent::class, FragmentComponent::class])
class AppModule(val app: TomtawApplication) {
    init {
        App(app)
        Constant.vm = BR.vm
//        Constant.inflate = BR.inflate
        if(BuildConfig.DEBUG){
            ARouter.openDebug()
            ARouter.openLog()
            Timber.plant(Timber.DebugTree())
        }
        ARouter.init(app)
    }

    @AppContext
    @Provides
    @ApplicationScope
    internal fun getContext(): Context {
        return app
    }

    @AppContext
    @Provides
    @ApplicationScope
    internal fun getApplication(): TomtawApplication {
        return app
    }

    @AppContext
    @Provides
    @ApplicationScope
    internal fun provideResources(): Resources {
        return app.resources
    }


}