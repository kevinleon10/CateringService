package com.hatchworks.cateringservice.di

import com.hatchworks.cateringservice.viewmodel.FeaturedCateringServicesViewModel
import com.hatchworks.cateringservice.viewmodel.UpcomingOrdersViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ViewModelComponent {

    fun inject(viewModel: FeaturedCateringServicesViewModel)

    fun inject(viewModel: UpcomingOrdersViewModel)
}