package com.eWord.inquiry.inject.data.oss

import android.annotation.SuppressLint
import com.alibaba.sdk.android.oss.common.auth.OSSCustomSignerCredentialProvider
import com.eWord.inquiry.inject.data.net.HttpApi
import com.eWord.inquiry.inject.data.net.transform.ErrorSingleTransformer
import timber.log.Timber


class OssSignCredentialProvider(private val httpApi: HttpApi) : OSSCustomSignerCredentialProvider() {

    private var result = ""
    private val obj = Object()

    @SuppressLint("CheckResult")
    @Synchronized
    override fun signContent(content: String): String {
        try {
            httpApi.ossApi(content)
                .compose(ErrorSingleTransformer())
                .map { it.token }
                .subscribe({this.accept(it)},{it.printStackTrace()})
            synchronized(obj) { obj.wait() }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Timber.i("oss:%1s", result)
        return result
    }

    private fun accept(string: String) {
        synchronized(obj) {
            result = string
            obj.notify()
        }
    }
}
