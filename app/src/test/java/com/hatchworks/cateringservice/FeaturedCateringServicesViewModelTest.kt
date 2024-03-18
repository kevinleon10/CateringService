package com.hatchworks.cateringservice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hatchworks.cateringservice.api.CateringServiceApiService
import com.hatchworks.cateringservice.model.*
import com.hatchworks.cateringservice.viewmodel.FeaturedCateringServicesViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

class FeaturedCateringServicesViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var cateringServiceApiService: CateringServiceApiService

    @InjectMocks
    var featuredCateringServicesViewModel = FeaturedCateringServicesViewModel()

    private var testSingle: Single<List<FeaturedCateringService>>? = null

    // TODO: Fix test
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    // TODO: Fix test
    @Test
    fun getCateringServicesSuccess() {
        val cateringService = CateringService(
            "name",
            "type",
            arrayListOf("imageUrls"),
            10,
            "description",
            "preparation Time",
            10,
            20,
            "priceRange",
            Menu(
                arrayListOf(
                    FoodTime("name", arrayListOf(Saucer("name", "type", "imageUrl", "description")))
                )
            ),
            arrayListOf("timeAvailable")
        )
        val featuredCateringService = FeaturedCateringService("title", arrayListOf(cateringService))
        val featuredCateringServiceList = arrayListOf(featuredCateringService)

        testSingle = Single.just(featuredCateringServiceList)

        `when`(cateringServiceApiService.getFeaturedCateringServices()).thenReturn(testSingle)

        featuredCateringServicesViewModel.refresh()

        Assert.assertEquals(
            1,
            featuredCateringServicesViewModel.featuredCateringServices.value?.size
        )
        Assert.assertEquals(false, featuredCateringServicesViewModel.loadError.value)
        Assert.assertEquals(false, featuredCateringServicesViewModel.loading.value)
    }

    @Test
    fun getCateringServicesFail(){
        testSingle = Single.error(Throwable())

        `when`(cateringServiceApiService.getFeaturedCateringServices()).thenReturn(testSingle)

        featuredCateringServicesViewModel.refresh()

        Assert.assertEquals(true, featuredCateringServicesViewModel.loadError.value)
        Assert.assertEquals(false, featuredCateringServicesViewModel.loading.value)
    }

    @Before
    fun setupRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, true)
            }

        }

        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }
}