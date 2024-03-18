package com.hatchworks.cateringservice.di

import com.hatchworks.cateringservice.api.CateringServiceApiService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CateringServiceApiService)
}