package com.eWord.inquiry.base.life.recycler

import com.eWord.inquiry.base.life.viewmodel.BaseDiffListViewModel
import com.eWord.inquiry.inject.data.Api
import com.lifecycle.binding.inter.inflate.DiffInflate
import io.reactivex.Single

class RecyclerModel<E:DiffInflate>: BaseDiffListViewModel<E>(){
    lateinit var  block:(Api,Int,Int)->Single<MutableList<E>>
    override fun getData(api: Api, position: Int, state: Int): Single<MutableList<E>> {
        return block(api,position,state)
    }
}