package com.eWord.inquiry.ui.start

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.alibaba.android.arouter.facade.annotation.Route
import com.eWord.inquiry.R
import com.eWord.inquiry.base.life.anko.AnkoActivity
import com.eWord.inquiry.inject.component.ActivityComponent
import com.eWord.inquiry.inject.data.Api
import com.eWord.inquiry.ui.ARouterUtil
import com.eWord.inquiry.ui.TomtawApplication
import com.eWord.inquiry.base.util.rxBusMain
import com.eWord.inquiry.ui.start.StartActivity.Companion.start
import org.jetbrains.anko.*

@Route(path = start)
class StartActivity : AnkoActivity<StartModel>() {
    companion object {
        const val start = ActivityComponent.Config.tomtaw + "start"
    }

    override fun initView(savedInstanceState: Bundle?): View {
        return if (TomtawApplication.activityBuilder == null) {
            AnkoContext.create(this).apply {
                frameLayout {
                    imageView { imageResource = R.drawable.ic_launcher_background }
                    rxBusMain<ActivityComponent.Builder>()
                        .subscribe {
                            removeAllViews()
                            addView(super.inject(it, savedInstanceState))
                        }
                }
            }.view
        } else super.initView(savedInstanceState)
    }

    override fun initData(api: Api, owner: LifecycleOwner, bundle: Bundle?) {
        super.initData(api, owner, bundle)
        ARouterUtil.start()
        finish()
    }

    override fun parse(t: StartModel, context: Context): AnkoContext<Context> {
        return AnkoContext.create(this).apply {
            frameLayout {
                imageView {
                    imageResource = R.mipmap.ic_launcher
                }
            }
        }
    }

}