package com.eWord.inquiry.inject.data.net.transform

import com.eWord.inquiry.inject.data.net.InfoEntity
import com.eWord.inquiry.inject.data.net.exception.ApiException
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import timber.log.Timber

/**
 * Company:
 * Description:
 * Author: created by ArvinWang on 2019/10/10 13:24
 * Email: 1033144294@qq.com
 */
class RestfulSingleTransformer<T> : SingleTransformer<InfoEntity<T>, T> {

    override fun apply(upstream: Single<InfoEntity<T>>): SingleSource<T> {
        return upstream.flatMap {
            Single.create<T> { emitter ->  try {
                if(it.result!=null){
                    emitter.onSuccess(it.result)
                }else emitter.onError(ApiException(it.code,it.message))
            }catch (e:Exception){
                emitter.onError(ApiException(it.code,it.message))
            } }
        }
    }

}