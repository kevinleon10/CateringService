package com.hatchworks.cateringservice.api

import com.hatchworks.cateringservice.model.FeaturedCateringService
import com.hatchworks.cateringservice.model.UpcomingOrder
import io.reactivex.Single
import retrofit2.http.GET

interface CateringServiceApi {

    @GET("kevinleon10/CateringService/main/featuredCateringServices.json")
    fun getFeaturedCateringServices(): Single<List<FeaturedCateringService>>

    @GET("kevinleon10/CateringService/main/upcomingOrders.json")
    fun getUpcomingOrders(): Single<List<UpcomingOrder>>

}