package com.barghouthi.myapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String,
) : Parcelable
