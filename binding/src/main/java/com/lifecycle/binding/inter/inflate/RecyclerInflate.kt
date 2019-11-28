package com.lifecycle.binding.inter.inflate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.lifecycle.binding.Constant
import com.lifecycle.binding.R

interface RecyclerInflate : Inflate, Recycler {

    override fun createView(context: Context, parent: ViewGroup, convertView: View?): View {
        val binding: ViewDataBinding = convertView.let {
            val layoutId = convertView?.getTag(R.id.inflate)?.let { (it as Inflate).layoutId() }
            convertView?.getTag(R.id.dataBinding).let {
                if (it is ViewDataBinding && layoutId == layoutId()) {
                    it.setVariable(Constant.parse, this)
                    it.setVariable(Constant.vm, this)
                    it.executePendingBindings()
                    it
                } else {
                    val b = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId(), parent, false) as ViewDataBinding
                    b.setVariable(Constant.parse,this)
                    b.setVariable(Constant.vm,this)
                    b
                }
            }
        }
        val view = binding.root
        view.setTag(R.id.dataBinding, binding)
        view.setTag(R.id.inflate, this)
        return view
    }

}