package com.barghouthi.myapp.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.barghouthi.myapp.R
import com.barghouthi.myapp.databinding.FragmentSearchBinding
import com.barghouthi.myapp.data.models.Country
import com.barghouthi.myapp.ui.activities.MainActivity
import com.barghouthi.myapp.ui.adpaters.CountriesAdapter


class SearchFragment : Fragment(){

    lateinit var binding: FragmentSearchBinding
    lateinit var searchView: SearchView
    lateinit var countries: List<Country>
    var savedInstanceState: Bundle? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search,container,false)
        countries = (arguments?.get("countries") as Array<Country>).toList()


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.searchRecycleView.layoutManager = LinearLayoutManager(activity)

        binding.searchRecycleView.adapter = CountriesAdapter(null){
                bundle, _ ->

            searchView.clearFocus()
            findNavController().navigate(R.id.action_searchFragment_to_detailsFragment,bundle)

        }
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.search_view_action,menu)
        val myMenuItem: MenuItem = menu.findItem(R.id.action_search)

        val mainActivity = activity as MainActivity
        searchView =  SearchView(mainActivity.supportActionBar!!.themedContext)

        myMenuItem.actionView = searchView

        savedInstanceState?.apply {
            val query = getString("searchView.query")
            searchView.setQuery(query,false)
            val adapter = (binding.searchRecycleView.adapter as CountriesAdapter)
            adapter.countries = countrySearch(query)
            adapter.notifyDataSetChanged()
        }

        searchView.setOnCloseListener {
            true
        }


        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {


                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {

                val countriesResult = countrySearch(newText)

                (binding.searchRecycleView.adapter as CountriesAdapter).countries = countriesResult
                (binding.searchRecycleView.adapter)?.notifyDataSetChanged()
                return true
            }

        })


        searchView.isIconified = false
        searchView.isFocusable = true
        searchView.requestFocus()



    }

    fun countrySearch(query: String?) : List<Country>{

        return countries.filter {

            it.name.contains(query.toString(),true)
        }


    }





    override fun onPause() {
        super.onPause()
        savedInstanceState = bundleOf("searchView.query" to searchView.query.toString())
    }
}