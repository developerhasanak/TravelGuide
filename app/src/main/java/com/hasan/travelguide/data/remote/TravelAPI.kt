package com.hasan.travelguide.data.remote

import com.hasan.travelguide.domain.model.remotemodel.AllTravelListItem
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TravelAPI {

    @GET("AllTravelList?category=hotel|flight|transportation")
    suspend fun getDealsAllList(): Response<List<AllTravelListItem>>

    @GET("AllTravelList")
   suspend fun getCategoriesList(
        @Query("category") category : String
   ):Response<List<AllTravelListItem>>
}