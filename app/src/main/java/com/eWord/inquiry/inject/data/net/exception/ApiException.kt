package com.eWord.inquiry.inject.data.net.exception

import java.lang.RuntimeException

/**
 * Company:
 * Description:
 * Author: created by ArvinWang on 2019/10/9 17:20
 * Email: 1033144294@qq.com
 */
open class ApiException(val code:String="",msg:String = "") :RuntimeException(msg)