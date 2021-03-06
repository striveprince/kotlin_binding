package com.eWord.inquiry.inject.interceptor

import com.eWord.inquiry.inject.data.preference.setting.SettingApi
import com.eWord.inquiry.inject.data.preference.user.UserApi
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class NetInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val r = request.newBuilder()
            .addHeader("x-token", UserApi.token)
            .addHeader("x-deviceno", SettingApi.deviceId)
            .addHeader("x-client-type", "2")
            .build()
        return chain.proceed(r)
    }
}