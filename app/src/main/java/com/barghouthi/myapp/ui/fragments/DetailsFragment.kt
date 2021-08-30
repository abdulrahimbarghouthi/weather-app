package com.barghouthi.myapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.barghouthi.myapp.R
import com.barghouthi.myapp.databinding.FragmentDetailsBinding
import com.barghouthi.myapp.data.models.Country


class DetailsFragment : Fragment(){

    val TAG = "DetailsFragment"

    lateinit var country: Country
    lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        country = arguments?.getParcelable("selectedCountry")!!
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_details,container,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)

    }
}