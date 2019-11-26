package com.customers.zktc.ui.home

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.facade.annotation.Route
import com.customers.zktc.R
import com.customers.zktc.base.life.anko.AnkoActivity
import com.customers.zktc.inject.component.ActivityComponent
import com.customers.zktc.inject.data.Api
import com.lifecycle.binding.util.bottomNavigationView
import com.lifecycle.binding.util.observer
import org.jetbrains.anko.*
/**
 * Company:
 * Description:
 * Author: created by ArvinWang on 2019/11/15 9:51
 * Email: 1033144294@qq.com
 */
@Route(path = HomeActivity.home)
class HomeActivity : AnkoActivity<HomeModel>() {
    companion object{const val home = ActivityComponent.Config.tomtaw+"home"}
    private var beforeIndex = -1
    private val homeEntities = ArrayList<HomeEntity>()

    override fun parse(t: HomeModel, context: Context): AnkoContext<Context> {
        return AnkoContext.create(this).apply {
            verticalLayout {
                frameLayout { id = R.id.home_frame_layout }
                    .lparams(matchParent, wrapContent, 1f)
                bottomNavigationView{
                    itemIconTintList = resources.getColorStateList(R.color.tab_color)
                    inflateMenu(R.menu.home_tab)
                }.lparams(matchParent,dip(50))
            }
        }
    }

    override fun initData(api: Api, owner: LifecycleOwner, bundle: Bundle?) {
        super.initData(api, owner, bundle)
        for (index in 0..5) homeEntities.add(HomeEntity(index))
        model.currentIndex.observer(this){ checkFragment(it) }
    }

    private fun checkFragment(position: Int) {
        if(position<0||position>=homeEntities.size||position == beforeIndex)return
        val ft = supportFragmentManager.beginTransaction()
        if(beforeIndex>=0) ft.hide(homeEntities[beforeIndex].fragment)
        val fragment = homeEntities[position].fragment
        if(!fragment.isAdded)ft.add(R.id.home_frame_layout,fragment)
        ft.show(fragment)
        ft.commitAllowingStateLoss()
        beforeIndex = position
    }

}

