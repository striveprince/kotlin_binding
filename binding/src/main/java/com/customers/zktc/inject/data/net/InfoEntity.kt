package com.customers.zktc.inject.data.net

import kotlinx.serialization.Serializable

/**
 * Company:
 * Description:
 * Author: created by ArvinWang on 2019/10/10 13:19
 * Email: 1033144294@qq.com
 */
@Serializable
data class InfoEntity<T>(val result:T?, val message :String, val code:String,val errorType:String="")