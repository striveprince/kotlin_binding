package com.eWord.inquiry.ui.home.message

import com.eWord.inquiry.base.life.recycler.RecyclerFragment
import com.lifecycle.binding.inter.inflate.DiffInflate
import io.reactivex.Single

class HomeMessageFragment :RecyclerFragment<DiffInflate>(){
    override fun getData(position: Int, state: Int): Single<MutableList<DiffInflate>> {
        return Single.just(ArrayList())
    }
}