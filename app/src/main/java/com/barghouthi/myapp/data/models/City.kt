package com.barghouthi.myapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    val id: Long,
    val name: String,
    val coord: Map<String,Double>,
    val country: String,
    val population: Long,
    val timezone: Int,

    ) :Parcelable

