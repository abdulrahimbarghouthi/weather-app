package com.barghouthi.myapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barghouthi.myapp.data.remote.weather.WeatherRepository
import com.barghouthi.myapp.data.models.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class WeatherViewModel: ViewModel() {

    val TAG = "WeatherViewModel"

    val response = MutableLiveData<Response<WeatherResponse>>()

    private val repository = WeatherRepository()

    fun getWeatherByCityName(
       cityName: String,
       cnt: Int,
    ){
        viewModelScope.launch(Dispatchers.IO) {

            val tempResponse = repository.getWeatherByCityName(cityName,cnt)

            withContext(Dispatchers.Main){
                response.value = tempResponse
            }

        }

    }

}