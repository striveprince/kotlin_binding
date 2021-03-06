package com.lifecycle.binding.adapter.databinding

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.InverseBindingMethod
import androidx.databinding.InverseBindingMethods
import androidx.databinding.adapters.ListenerUtil
import com.lifecycle.binding.R
import com.google.android.material.tabs.TabLayout
import com.lifecycle.binding.util.ReflectUtil

/**
 * Created by arvin on 2018/1/17.
 */

@InverseBindingMethods(
    InverseBindingMethod(
        type = TabLayout::class,
        attribute = "position",
        event = "positionAttrChanged",
        method = "getSelectedTabPosition"
    )
)
object TabLayoutBindingAdapter {

    @BindingAdapter("position")
    fun setScrollPosition(layout: TabLayout, position: Int) {
        val current = layout.selectedTabPosition
        if (current == position || position < 0) return
        val tab = layout.getTabAt(position)!!
        ReflectUtil.invoke("selectTab", layout, tab)
        layout.setScrollPosition(position, 0f, true)
    }

    @BindingAdapter(value = [ "tab_selected", "positionAttrChanged" ], requireAll = false)
    fun addOnTabSelectedListener(
        layout: TabLayout,
        listener: TabLayout.OnTabSelectedListener?,
        positionAttrChanged: InverseBindingListener?
    ) {
        val newValue = object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                listener?.onTabSelected(tab)
                positionAttrChanged?.onChange()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                listener?.onTabUnselected(tab)
            }

           override fun onTabReselected(tab: TabLayout.Tab) {
                listener?.onTabReselected(tab)
            }
        }
        val oldValue = ListenerUtil.trackListener(layout, newValue, R.id.tab_layout)
        if (oldValue != null) layout.removeOnTabSelectedListener(oldValue)
        layout.addOnTabSelectedListener(newValue)
    }
}
