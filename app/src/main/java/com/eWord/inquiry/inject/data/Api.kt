package com.eWord.inquiry.inject.data

import android.content.Context
import com.eWord.inquiry.inject.data.database.DatabaseApi
import com.eWord.inquiry.inject.data.map.MapApi
import com.eWord.inquiry.inject.data.net.NetApi
import com.eWord.inquiry.inject.data.oss.OssApi
import com.eWord.inquiry.inject.data.preference.PreferenceApi

class Api(
    private val application: Context,
    val netApi: NetApi,
    val databaseApi: DatabaseApi,
    val mapApi: MapApi,
    val ossApi: OssApi,
    val preferenceApi: PreferenceApi
) {

}
