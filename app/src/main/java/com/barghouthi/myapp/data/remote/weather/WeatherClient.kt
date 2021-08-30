package com.barghouthi.myapp.data.remote.weather

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherClient {

    private const val baseUrl = "https://api.openweathermap.org/data/2.5/forecast/"

    private val instance = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherService: WeatherService by lazy{
        instance.create(WeatherService::class.java)
    }

}