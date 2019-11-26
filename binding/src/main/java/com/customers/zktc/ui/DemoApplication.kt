package com.customers.zktc.ui

import androidx.multidex.MultiDexApplication
import com.customers.zktc.inject.component.ActivityComponent
import com.customers.zktc.inject.component.DaggerAppComponent
import com.customers.zktc.inject.component.FragmentComponent
import com.customers.zktc.inject.module.AppModule
import com.lifecycle.binding.util.RxBus
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
class DemoApplication : MultiDexApplication() {
    @Inject lateinit var activityBuilder: ActivityComponent.Builder
    @Inject lateinit var fragmentBuilder: FragmentComponent.Builder

    companion object {
        lateinit var application:DemoApplication
    }

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.Default).launch {
            DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .build().inject(application)
            application = this@DemoApplication
            RxBus.getInstance().send(application)
        }
    }
}