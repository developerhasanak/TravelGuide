package com.hasan.travelguide.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasan.travelguide.data.repository.TravelRepository
import com.hasan.travelguide.domain.model.remotemodel.AllTravelListItem
import com.hasan.travelguide.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    private val repository: TravelRepository
) :ViewModel()  {

    val allList = MutableLiveData<Resource<List<AllTravelListItem>>>()
    val flightsList = MutableLiveData<Resource<List<AllTravelListItem>>>()
    val hotelsList = MutableLiveData<Resource<List<AllTravelListItem>>>()
    val transpotationList = MutableLiveData<Resource<List<AllTravelListItem>>>()

    init {
        getAllApiData()
        getFlightsApiData()
        getHotelsApiData()
        getTransportationApiData()
    }

    private fun getAllApiData(){
        allList.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getAllListItem()
            allList.value = response
        }
    }

    private fun getFlightsApiData(){
        flightsList.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getCategoryList("flight")
            flightsList.value = response
        }
    }

    private fun getHotelsApiData(){
        hotelsList.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getCategoryList("hotel")
            hotelsList.value = response
        }
    }

    private fun getTransportationApiData(){
        transpotationList.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.getCategoryList("transportation")
           transpotationList.value = response
        }
    }

}