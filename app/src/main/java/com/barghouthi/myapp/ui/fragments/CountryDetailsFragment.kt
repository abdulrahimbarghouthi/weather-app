package com.barghouthi.myapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import coil.load
import com.barghouthi.myapp.ui.activities.MainActivity
import com.barghouthi.myapp.R
import com.barghouthi.myapp.databinding.FragmentCountryDetailsBinding
import com.barghouthi.myapp.data.models.Country

class CountryDetailsFragment : Fragment(){
    lateinit var binding: FragmentCountryDetailsBinding
    lateinit var country: Country

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as MainActivity).supportActionBar?.title = "Details"

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_country_details,container,false)
        country =  ((parentFragment?.parentFragment) as DetailsFragment).country
        binding.country = country


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.detailsFlag.load("http://www.geognos.com/api/en/countries/flag/${country.alpha2Code}.png")
    }
}