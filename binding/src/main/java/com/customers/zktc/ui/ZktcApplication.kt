package com.customers.zktc.ui

import androidx.multidex.MultiDexApplication
import com.customers.zktc.inject.component.AppComponent
import com.customers.zktc.inject.component.DaggerAppComponent
import com.customers.zktc.inject.module.AppModule
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
class ZktcApplication:MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        val application = this
        CoroutineScope(Dispatchers.Default).launch{
            DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .build().inject(application)
        }
    }
}