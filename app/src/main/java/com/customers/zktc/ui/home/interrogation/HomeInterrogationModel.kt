package com.customers.zktc.ui.home.interrogation

import com.customers.zktc.base.life.viewmodel.list.ListDiffViewModel
import com.customers.zktc.inject.data.Api
import com.lifecycle.binding.inter.inflate.DiffInflate
import io.reactivex.Observable
import io.reactivex.Single

class HomeInterrogationModel: ListDiffViewModel<HomeInterrogationFragment,DiffInflate>() {
    override fun getData(api: Api, state: Int, position: Int): Single<MutableList<DiffInflate>> {
        return Single.just(ArrayList())
    }
}