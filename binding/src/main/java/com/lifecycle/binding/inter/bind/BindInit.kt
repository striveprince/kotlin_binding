package com.lifecycle.binding.inter.bind

import androidx.databinding.ViewDataBinding
import com.lifecycle.binding.util.findLayoutView

/**
 * Company:
 * Description:
 * Author: created by ArvinWang on 2019/11/15 10:28
 * Email: 1033144294@qq.com
 */
interface BindInit<T,Binding : ViewDataBinding,Api> :BindParse<T,Binding,Api> {
    override fun layoutId()=findLayoutView(javaClass).layout[0]

}