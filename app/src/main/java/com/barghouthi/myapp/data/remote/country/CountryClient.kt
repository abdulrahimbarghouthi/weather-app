package com.barghouthi.myapp.data.remote.country

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountryClient {
    private val baseUrl = "https://restcountries.eu/rest/v1/"

    private val instance = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val countryService: CountryService by lazy{
        instance.create(CountryService::class.java)
    }


}