package com.hatchworks.cateringservice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hatchworks.cateringservice.api.CateringServiceApiService
import com.hatchworks.cateringservice.model.*
import com.hatchworks.cateringservice.viewmodel.UpcomingOrdersViewModel
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

class UpcomingOrdersViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var cateringServiceApiService: CateringServiceApiService

    @InjectMocks
    var upcomingOrdersViewModel = UpcomingOrdersViewModel()

    private var testSingle: Single<List<UpcomingOrder>>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    // TODO: Fix test
    @Test
    fun getUpcomingOrdersSuccess() {
        val upcomingOrder = UpcomingOrder("name", "date", "imageUrl", 10, 10)
        val upComingOrderList = arrayListOf(upcomingOrder)

        testSingle = Single.just(upComingOrderList)

        `when`(cateringServiceApiService.getUpcomingOrders()).thenReturn(testSingle)

        upcomingOrdersViewModel.refresh()

        Assert.assertEquals(
            1,
            upcomingOrdersViewModel.upcomingOrders.value?.size
        )
        Assert.assertEquals(false, upcomingOrdersViewModel.loadError.value)
        Assert.assertEquals(false, upcomingOrdersViewModel.loading.value)
    }

    // TODO: Fix test
    @Test
    fun getUpcomingOrdersFail(){
        testSingle = Single.error(Throwable())

        `when`(cateringServiceApiService.getUpcomingOrders()).thenReturn(testSingle)

        upcomingOrdersViewModel.refresh()

        Assert.assertEquals(true, upcomingOrdersViewModel.loadError.value)
        Assert.assertEquals(false, upcomingOrdersViewModel.loading.value)
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