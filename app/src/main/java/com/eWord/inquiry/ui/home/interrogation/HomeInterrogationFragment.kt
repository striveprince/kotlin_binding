package com.eWord.inquiry.ui.home.interrogation

import com.eWord.inquiry.base.life.recycler.RecyclerFragment
import com.lifecycle.binding.inter.inflate.DiffInflate
import io.reactivex.Single

class HomeInterrogationFragment: RecyclerFragment<HomeInterrogationEntity>() {
    override fun getData(position: Int, state: Int): Single<MutableList<HomeInterrogationEntity>> {
//        api.netApi.httpApi.download()
        val list = ArrayList<HomeInterrogationEntity>()
        for (index in 0..3){
            list.add(HomeInterrogationEntity())
        }
        return Single.just(list)
    }
}