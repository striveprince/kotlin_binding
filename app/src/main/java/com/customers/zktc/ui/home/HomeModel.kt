package com.customers.zktc.ui.home

import androidx.lifecycle.MutableLiveData
import com.google.android.material.tabs.TabLayout

/**
 * Company:
 * Description:
 * Author: created by ArvinWang on 2019/11/15 10:23
 * Email: 1033144294@qq.com
 */
class HomeModel : BaseViewModel() , TabLayout.OnTabSelectedListener{

    val currentIndex = MutableLiveData(-1)


    override fun onTabReselected(p0: TabLayout.Tab?) {

    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {

    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        currentIndex.value = p0?.position
    }
}