package com.eWord.inquiry.inject.data.net.transform

import com.eWord.inquiry.inject.data.net.InfoEntity
import com.eWord.inquiry.inject.data.net.exception.ApiException
import com.eWord.inquiry.inject.data.net.exception.AuthenticationException
import com.eWord.inquiry.inject.data.net.exception.LogoutException
import com.eWord.inquiry.inject.data.net.exception.TokenExpireException
import com.lifecycle.binding.util.fromJson
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import retrofit2.HttpException
import java.util.*

/**
 * Company:
 * Description:
 * Author: created by ArvinWang on 2019/10/10 14:02
 * Email: 1033144294@qq.com
 */
class ErrorObservableTransformer<T> : ObservableTransformer<T, T> {
    private val success = "200"
    private val tokenExpire = "1"
    private val authenticationException = "user"
    private val logout = "us"

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(Schedulers.io())
            .onErrorResumeNext (Function{ throwable ->
                var code = ""
                var msg = ""
                when (throwable) {
                    is HttpException -> try {
                        val infoEntity =
                            throwable.response()?.errorBody()!!.string().fromJson<InfoEntity<*>>()
                        code = "200"
                        msg = infoEntity.message
                    } catch (e: Exception) {
                        val thisCode = throwable.code()
                        code = "" + throwable.code()
                        when (thisCode) {
                            402 -> "数据库连接错误" + throwable.message()
                            403 -> "无记录" + throwable.message()
                            405 -> "token无效" + throwable.message()
                            400 -> "参数为空" + throwable.message()
                            else -> "网络请求失败" + throwable.message()
                        }
                    }
                    is ServiceConfigurationError -> msg = "服务器错误"
                    is JSONException -> msg = "数据解析错误"
                }
                Observable.just(ApiException(code, msg))
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap { Observable.error<T>(it) }
            })
            .flatMap {
                Observable.create<T> { emitter ->
                    if (it is InfoEntity<*>) {
                        when (it.code) {
                            success -> emitter.onNext(it)
                            tokenExpire -> emitter.onError(TokenExpireException(it.code, it.message))
                            logout -> emitter.onError(LogoutException(it.code, it.message))
                            authenticationException -> emitter.onError(AuthenticationException(it.code, it.message))
                            else -> emitter.onError(ApiException(it.code, it.message))
                        }
                    }
                }
            }
    }
}