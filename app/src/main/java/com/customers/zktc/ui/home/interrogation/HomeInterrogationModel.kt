package com.customers.zktc.ui.home.interrogation

import com.customers.zktc.base.life.viewmodel.BaseDiffListViewModel
import com.customers.zktc.inject.data.Api
import com.lifecycle.binding.inter.inflate.DiffInflate
import io.reactivex.Single

class HomeInterrogationModel: BaseDiffListViewModel<HomeInterrogationFragment, DiffInflate>() {
    override fun getData(api: Api, state: Int, position: Int): Single<MutableList<DiffInflate>> {
        return Single.just(ArrayList())
    }
}