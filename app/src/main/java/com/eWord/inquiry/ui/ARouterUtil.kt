package com.eWord.inquiry.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.eWord.inquiry.R
import com.eWord.inquiry.ui.home.HomeActivity

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