package com.customers.zktc.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.customers.zktc.R
import com.customers.zktc.ui.home.HomeActivity

object ARouterUtil {
    private fun build(path: String, bundle: Bundle = Bundle()): Postcard {
        return ARouter.getInstance()
            .build(path)
            .withTransition(R.anim.push_right_in, R.anim.push_left_out)
            .with(bundle)
    }

    fun start() {
        build(HomeActivity.home)
            .navigation()
    }
}