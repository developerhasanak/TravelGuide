package com.hasan.travelguide.presentation.search

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
class SearchViewModel @Inject constructor(
    private val repository: TravelRepository
    ):ViewModel() {

   val topDectinations = MutableLiveData<Resource<List<AllTravelListItem>>>()

    init {
        getTopDestinationData()
    }

   private fun getTopDestinationData(){
       topDectinations.value = Resource.loading(null)
       viewModelScope.launch {
           val response = repository.getCategoryList("topdestination")
           topDectinations.value = response
       }
   }

}