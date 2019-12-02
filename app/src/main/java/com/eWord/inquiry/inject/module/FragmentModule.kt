package com.eWord.inquiry.inject.module

import android.content.Context
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.eWord.inquiry.inject.qualifier.ChildFragmentManager
import com.eWord.inquiry.inject.qualifier.FragmentContext
import com.eWord.inquiry.inject.qualifier.SupportFragmentManager
import com.eWord.inquiry.inject.scope.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class FragmentModule (private val fragment:Fragment){

    @FragmentScope
    @FragmentContext
    @Provides
    fun provideFragmentContext():Context{
        return fragment.activity!!
    }

    @SupportFragmentManager
    @Provides
    @FragmentScope
    fun provideFragmentManager():FragmentManager{
        return fragment.fragmentManager!!
    }

    @ChildFragmentManager
    @Provides
    @FragmentScope
    fun provideChildFragmentManager():FragmentManager{
        return fragment.childFragmentManager
    }

    @FragmentScope
    @Provides
    internal fun provideDisplayMetrics(@FragmentContext context: Context): DisplayMetrics {
        return context.resources.displayMetrics
    }

}