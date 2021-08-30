package com.barghouthi.myapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.barghouthi.myapp.R
import com.barghouthi.myapp.databinding.FragmentWeatherBinding
import com.barghouthi.myapp.data.models.Country
import com.barghouthi.myapp.ui.activities.MainActivity
import com.barghouthi.myapp.ui.adpaters.WeatherPagerAdapter
import com.barghouthi.myapp.viewmodels.WeatherViewModel
import com.google.android.material.tabs.TabLayoutMediator


class WeatherFragment : Fragment(){

    val TAG = "WeatherFragment"

    val tabs = arrayOf("Today","Tomorrow")

    lateinit var binding: FragmentWeatherBinding
    lateinit var viewModel : WeatherViewModel
    lateinit var country : Country

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        country = (parentFragment?.parentFragment as DetailsFragment).country

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_weather,container,false)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: ")
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        viewModel.getWeatherByCityName(country.name,2)


        
        viewModel.response.observe(viewLifecycleOwner){ response ->
            if(response.isSuccessful){


                Log.d(TAG, "response received")
                (activity as MainActivity).supportActionBar?.title = "Weather"

                binding.progressBar.visibility = View.GONE

                val pagerAdapter = WeatherPagerAdapter(this,response.body()!!)
                binding.viewPager.adapter = pagerAdapter

                TabLayoutMediator(binding.tabLayout,binding.viewPager){ tab , pos->
                    tab.text = tabs[pos]
                }.attach()

//                pagerAdapter.weatherResponse = response.body()
//
//                pagerAdapter.notifyDataSetChanged()


            }
        }


    }


}



