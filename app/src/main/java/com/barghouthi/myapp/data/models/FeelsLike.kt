package com.barghouthi.myapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeelsLike(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double,
) : Parcelable
