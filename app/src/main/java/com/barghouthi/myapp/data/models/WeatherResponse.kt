package com.barghouthi.myapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherResponse(
    val city: City,
    val cod: String,
    val message: Double,
    val cnt: Int,
    val list: List<Day>
) : Parcelable
