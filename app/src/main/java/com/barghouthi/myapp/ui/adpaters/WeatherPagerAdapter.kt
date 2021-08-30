package com.barghouthi.myapp.ui.adpaters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.barghouthi.myapp.data.models.WeatherResponse
import com.barghouthi.myapp.ui.fragments.*

class WeatherPagerAdapter(val fragment: Fragment,var weatherResponse: WeatherResponse) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int  = 2

    override fun createFragment(position: Int): Fragment {

        return WeatherTapFragment().apply {
            arguments = Bundle().apply {
                putInt("dayNo", position)
                putParcelable("weatherResponse", weatherResponse)
            }
        }

    }


}