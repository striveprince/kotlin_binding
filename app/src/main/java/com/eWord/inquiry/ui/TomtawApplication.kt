package com.eWord.inquiry.ui

import androidx.multidex.MultiDexApplication
import com.eWord.inquiry.base.util.busPost
import com.eWord.inquiry.inject.component.ActivityComponent
import com.eWord.inquiry.inject.component.DaggerAppComponent
import com.eWord.inquiry.inject.component.FragmentComponent
import com.eWord.inquiry.inject.module.AppModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Company:
 * Description:
 * Author: created by ArvinWang on 2019/11/15 10:50
 * Email: 1033144294@qq.com
 */
class TomtawApplication : MultiDexApplication() {
    @Inject lateinit var activityBuilder: ActivityComponent.Builder
    @Inject lateinit var fragmentBuilder: FragmentComponent.Builder

    companion object {
        lateinit var application:TomtawApplication
        var activityBuilder:ActivityComponent.Builder? = null
    }

    override fun onCreate() {
        super.onCreate()
        val context = this
        CoroutineScope(Dispatchers.Default).launch {
            DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .build().inject(context)
            application = this@TomtawApplication
            TomtawApplication.activityBuilder = activityBuilder
            busPost(activityBuilder)
        }
    }
}