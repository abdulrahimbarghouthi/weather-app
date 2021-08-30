package com.barghouthi.myapp.data.remote.country

import com.barghouthi.myapp.data.models.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryService {

    @GET("all")
    suspend fun getAllCountries() : Response<List<Country>>

}