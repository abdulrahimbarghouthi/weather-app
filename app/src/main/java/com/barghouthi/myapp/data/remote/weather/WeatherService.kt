package com.barghouthi.myapp.data.remote.weather

import com.barghouthi.myapp.data.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("daily")
    suspend fun getWeatherByCityName(
        @Query("q") cityName: String,
        @Query("cnt") cnt: Int,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ) : Response<WeatherResponse>


}