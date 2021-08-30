package com.barghouthi.myapp.data.remote.weather

import com.barghouthi.myapp.data.models.WeatherResponse
import retrofit2.Response


class WeatherRepository {

    val apiKey = "1867722b6af87e1d0388e10c5a94be34"

    suspend fun getWeatherByCityName(cityName: String,cnt: Int) : Response<WeatherResponse>{
        return WeatherClient.weatherService.getWeatherByCityName(cityName,cnt,apiKey,"metric")
    }

}