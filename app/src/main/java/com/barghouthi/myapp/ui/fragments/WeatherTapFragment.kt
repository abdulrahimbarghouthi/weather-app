package com.barghouthi.myapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import coil.load
import com.barghouthi.myapp.R
import com.barghouthi.myapp.databinding.FragmentWeatherTapBinding
import com.barghouthi.myapp.data.models.WeatherResponse


class WeatherTapFragment : Fragment() {
    val TAG = "WeatherTapFragment"
    private var weatherResponse: WeatherResponse? = null
    private var dayNo: Int? = null

    lateinit var binding: FragmentWeatherTapBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("WeatherTapFragment", "created ")
        arguments?.let {
            weatherResponse = it.getParcelable("weatherResponse")
            dayNo = it.getInt("dayNo")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_weather_tap,container,false)
        arguments?.let {
            binding.weatherResponse = this.weatherResponse
            binding.dayNo = this.dayNo
            binding.executePendingBindings()
        }

        val icon = weatherResponse?.list?.get(dayNo!!)?.weather?.get(0)?.icon
        binding.imageView2.load("http://openweathermap.org/img/wn/$icon@2x.png")

        return binding.root
    }







}