package com.hatchworks.cateringservice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hatchworks.cateringservice.di.DaggerViewModelComponent
import com.hatchworks.cateringservice.model.FeaturedCateringService
import com.hatchworks.cateringservice.api.CateringServiceApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FeaturedCateringServicesViewModel: ViewModel() {

    val featuredCateringServices by lazy { MutableLiveData<List<FeaturedCateringService>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    @Inject
    lateinit var disposable: CompositeDisposable

    @Inject
    lateinit var cateringServiceApiService: CateringServiceApiService

    init {
        DaggerViewModelComponent.create().inject(this)
    }

    fun refresh() {
        loadError.value = false
        featuredCateringServices.value = null
        loading.value = true
        getCateringServices()
    }

    private fun getCateringServices() {
        disposable.add(
            cateringServiceApiService.getFeaturedCateringServices()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<FeaturedCateringService>>() {
                    override fun onSuccess(t: List<FeaturedCateringService>) {
                        featuredCateringServices.value = t
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loadError.value = true
                        loading.value = false
                    }
                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}