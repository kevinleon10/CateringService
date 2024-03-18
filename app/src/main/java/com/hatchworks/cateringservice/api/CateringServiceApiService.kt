package com.hatchworks.cateringservice.api

import com.hatchworks.cateringservice.di.DaggerApiComponent
import com.hatchworks.cateringservice.model.FeaturedCateringService
import com.hatchworks.cateringservice.model.UpcomingOrder
import io.reactivex.Single
import javax.inject.Inject

class CateringServiceApiService {

    @Inject
    lateinit var api: CateringServiceApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getFeaturedCateringServices(): Single<List<FeaturedCateringService>> {
        return api.getFeaturedCateringServices()
    }

    fun getUpcomingOrders(): Single<List<UpcomingOrder>> {
        return api.getUpcomingOrders()
    }
}