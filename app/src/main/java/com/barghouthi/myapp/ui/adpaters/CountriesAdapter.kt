package com.barghouthi.myapp.ui.adpaters

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ImageRequest
import coil.request.ImageResult
import com.barghouthi.myapp.R
import com.barghouthi.myapp.data.models.Country

class CountriesAdapter( var countries: List<Country>?, val onClickListener: (Bundle, Int)->Unit) : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    //TODO implement Data binding

    private val TAG = "CountriesAdapter"


    class CountryViewHolder(val view: View): RecyclerView.ViewHolder(view){

        var countryName: TextView
        var region: TextView
        var countryImage: ImageView
        var anim : ObjectAnimator


        init {

            countryName = view.findViewById<TextView>(R.id.countryName)
            region = view.findViewById<TextView>(R.id.region)
            countryImage = view.findViewById<ImageView>(R.id.imageView)


            anim = ObjectAnimator.ofFloat(countryImage,View.ALPHA,0.2F,0.4F,0.9F,1.0F).apply {
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
                duration = 1000
            }

        }



        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {


        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.country_list_item, parent, false)

        return CountryViewHolder(view)
    }


    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {


        holder.countryName.text = countries?.get(position)?.name
        holder.region.text = countries?.get(position)?.region
        

        holder.countryImage.load("http://www.geognos.com/api/en/countries/flag/${countries?.get(position)?.alpha2Code}.png"){

            placeholder(R.drawable.flag_place_holder)

            listener(object : ImageRequest.Listener{
                override fun onCancel(request: ImageRequest) {
                    holder.anim.cancel()
                    holder.countryImage.alpha = 1F
                    Log.d(TAG, "onCancel: ${holder.adapterPosition}")
                }

                override fun onError(request: ImageRequest, throwable: Throwable) {
                    holder.countryImage.setImageResource(R.drawable.flag_place_holder)
                    Log.d(TAG, "onError: ${holder.adapterPosition}")
                }

                override fun onStart(request: ImageRequest) {
                    if(!holder.anim.isStarted and !holder.anim.isRunning) {
                        holder.anim.start()
                    }

                }

                override fun onSuccess(request: ImageRequest, metadata: ImageResult.Metadata) {
                    holder.anim.cancel()
                    holder.countryImage.alpha = 1F
                    Log.d(TAG, "onSuccess: ${holder.adapterPosition}")
                }
            })
        }

        holder.itemView.setOnClickListener{
            val bundle = bundleOf("selectedCountry" to countries!![holder.adapterPosition])
            onClickListener(bundle,position)

        }


    }

    override fun getItemCount(): Int  = countries?.size ?: 0
}



