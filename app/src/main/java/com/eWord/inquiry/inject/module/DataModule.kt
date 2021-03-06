package com.eWord.inquiry.inject.module

import android.content.Context
import com.eWord.inquiry.BuildConfig
import com.eWord.inquiry.inject.component.ActivityComponent
import com.eWord.inquiry.inject.component.FragmentComponent
import com.eWord.inquiry.inject.data.Api
import com.eWord.inquiry.inject.data.database.DatabaseApi
import com.eWord.inquiry.inject.data.map.MapApi
import com.eWord.inquiry.inject.data.net.HttpApi
import com.eWord.inquiry.inject.data.net.NetApi
import com.eWord.inquiry.inject.data.net.converter.JsonConverterFactory
import com.eWord.inquiry.inject.data.oss.OssApi
import com.eWord.inquiry.inject.data.preference.PreferenceApi
import com.eWord.inquiry.inject.interceptor.NetInterceptor
import com.eWord.inquiry.inject.qualifier.AppContext
import com.eWord.inquiry.inject.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

@Module(subcomponents = [ActivityComponent::class,FragmentComponent::class])
class DataModule {

    @Provides
    @ApplicationScope
    internal fun provideNetApi(okHttpClient: OkHttpClient,netInterceptor: NetInterceptor): HttpApi {
        val client = okHttpClient.newBuilder()
            .addInterceptor(netInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.ApiHost)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(JsonConverterFactory())
            .callFactory(client)
            .build().create(HttpApi::class.java)
    }

    @Provides
    @ApplicationScope
    internal fun provideOkHttpClient(): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor())
            //.cache(new Cache(new File(""),1024*1024*20))
            .build().newBuilder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return httpClientBuilder.build()
    }

    @Provides
    @ApplicationScope
    internal fun provideDataBaseApi():DatabaseApi{
        return DatabaseApi()
    }

    @Provides
    @ApplicationScope
    internal fun provideMapApi(@AppContext context: Context):MapApi{
        return MapApi(context)
    }

    @Provides
    @ApplicationScope
    internal fun provideOssApi(@AppContext context: Context, httpApi: HttpApi): OssApi {
        return OssApi(context,httpApi)
    }

    @Provides
    @ApplicationScope
    internal fun providePreferenceApi(@AppContext context: Context): PreferenceApi {
        return PreferenceApi(context)
    }

    @Provides
    @ApplicationScope
    internal  fun provideApi(@AppContext context: Context, httpApi: HttpApi, databaseApi:DatabaseApi, mapApi:MapApi, ossApi: OssApi, preferenceApi: PreferenceApi):Api{
//        JPushInterface.init(context)
//        JPushInterface.setDebugMode(BuildConfig.DEBUG)
        return Api(context,NetApi(httpApi),databaseApi,mapApi,ossApi,preferenceApi)
    }

}
