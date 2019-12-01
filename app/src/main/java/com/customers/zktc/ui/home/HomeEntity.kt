package com.customers.zktc.ui.home

import androidx.fragment.app.Fragment
import com.customers.zktc.R
import com.customers.zktc.ui.home.browse.HomeBrowseFragment
import com.customers.zktc.ui.home.interrogation.HomeInterrogationFragment
import com.customers.zktc.ui.home.message.HomeMessageFragment
import com.customers.zktc.ui.home.mine.HomeMineFragment
import com.google.android.material.tabs.TabLayout

/**
 * Company:
 * Description:
 * Author: created by ArvinWang on 2019/11/15 16:25
 * Email: 1033144294@qq.com
 */
class HomeEntity(p: Int) {
    val fragment: Fragment = when(p) {
        0->HomeInterrogationFragment()
        1->HomeBrowseFragment()
        2->HomeMessageFragment()
        else ->HomeMineFragment()
    }
}