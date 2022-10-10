package com.hasan.travelguide.presentation.home.deals.all


import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.hasan.travelguide.data.remote.TravelAPIService
import com.hasan.travelguide.domain.model.remotemodel.AllTravelListItem
import com.hasan.travelguide.presentation.home.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class AllfragmentViewModel(application: Application) : BaseViewModel(application) {

    private val disposable = CompositeDisposable()
    private val travelAPIService = TravelAPIService()

    val allList = MutableLiveData<List<AllTravelListItem>>()

    fun getDataFromApi() {

            disposable.add(
                travelAPIService.getDealsAllData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handlerResponse)
            )
    }

    private fun handlerResponse(listItem: List<AllTravelListItem>){

        allList.value = listItem
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
