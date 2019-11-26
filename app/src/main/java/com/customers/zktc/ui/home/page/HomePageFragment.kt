package com.customers.zktc.ui.home.page

import com.customers.zktc.R
import com.customers.zktc.base.life.binding.DataBindingFragment
import com.customers.zktc.databinding.LayoutRecyclerViewBinding
import com.lifecycle.binding.inter.bind.annotation.LayoutView

@LayoutView(layout = [R.layout.layout_recycler_view])
class HomePageFragment : DataBindingFragment<HomePageModel,LayoutRecyclerViewBinding>() {
}