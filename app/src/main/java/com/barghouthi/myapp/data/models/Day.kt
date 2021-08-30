package com.barghouthi.myapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Day(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Temperature,
    val feels_like: FeelsLike,
    val pressure: Double,
    val humidity: Int,
    val weather: List<Weather>,
    val speed: Double,
    val deg: Int,
    val gust: Double,
    val clouds: Int,
    val pop: Double,
) : Parcelable