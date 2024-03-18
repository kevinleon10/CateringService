package com.hatchworks.cateringservice.di

import com.hatchworks.cateringservice.api.CateringServiceApi
import com.hatchworks.cateringservice.api.CateringServiceApiService
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val baseUrl = "https://raw.githubusercontent.com"

    @Provides
    fun provideCateringServiceApi(): CateringServiceApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CateringServiceApi::class.java)
    }

    @Provides
    fun provideCateringServiceApiService(): CateringServiceApiService {
        return CateringServiceApiService()
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}