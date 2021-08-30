package com.barghouthi.myapp.data.remote.country

import com.barghouthi.myapp.data.models.Country
import retrofit2.Response

class CountryRepository {

    suspend fun getAllCountries() : Response<List<Country>>{
        return CountryClient.countryService.getAllCountries()
    }

}