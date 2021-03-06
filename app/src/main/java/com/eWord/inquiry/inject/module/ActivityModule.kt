package com.eWord.inquiry.inject.module

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.eWord.inquiry.inject.qualifier.ActivityContext
import com.eWord.inquiry.inject.qualifier.ActivityFragmentManager
import com.eWord.inquiry.inject.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    @ActivityScope
    internal fun provideActivity(): Context {
        return activity
    }

    @ActivityScope
    @ActivityFragmentManager
    @Provides
    internal fun provideFragmentManager(): FragmentManager {
        return activity.supportFragmentManager
    }

    @Provides
    @ActivityScope
    internal fun provideDisplayMetrics(): DisplayMetrics {
        return activity.resources.displayMetrics
    }

    @Provides
    @ActivityScope
    internal fun provideLayoutInflater(): LayoutInflater {
        return LayoutInflater.from(activity)
    }
}