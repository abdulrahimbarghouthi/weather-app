package com.barghouthi.myapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.barghouthi.myapp.viewmodels.MainViewModel
import com.barghouthi.myapp.R

import com.barghouthi.myapp.databinding.FragmentHomeBinding
import com.barghouthi.myapp.ui.adpaters.CountriesAdapter

class HomeFragment : Fragment(){

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        binding.countriesRecyclerView.layoutManager = LinearLayoutManager(container?.context)
        binding.countriesRecyclerView.setHasFixedSize(true)



        viewModel  = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.countries.observe(viewLifecycleOwner){
            Log.d("HomeFragment", "onCreate:response received ")

            binding.progressBar2.visibility = View.GONE

            if(it.isSuccessful){

                binding.countriesRecyclerView.adapter = CountriesAdapter(it.body()){
                    bundle, _ ->
                    findNavController().navigate(R.id.action_homeFragment_to_detailsFragment,bundle)
                }
            }

        }

        return binding.root

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_action,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.searchAction -> {
                val countries = viewModel.countries.value?.body()?.toTypedArray()
                val bundle = bundleOf("countries" to countries)
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment,bundle)
            }
        }

        return true
    }







}