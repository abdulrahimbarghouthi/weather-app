package com.barghouthi.myapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.barghouthi.myapp.data.remote.country.CountryRepository
import com.barghouthi.myapp.data.models.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainViewModel() : ViewModel() {

    private val TAG = "MainViewModel"

    private val countryRepository: CountryRepository = CountryRepository()
    val countries: MutableLiveData<Response<List<Country>>> = MutableLiveData()

    fun getAllCountries(){
        viewModelScope.launch(Dispatchers.IO){

            val tempCountries =  countryRepository.getAllCountries()

            withContext(Dispatchers.Main){
                countries.value  = tempCountries
            }



        }
    }

    init {
        getAllCountries()
    }

}