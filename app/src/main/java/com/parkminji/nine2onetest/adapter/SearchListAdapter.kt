package com.parkminji.nine2onetest.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parkminji.nine2onetest.R
import com.parkminji.nine2onetest.SearchWebViewActivity
import com.parkminji.nine2onetest.model.Place
import kotlinx.android.synthetic.main.search_result_item.view.*

class SearchListAdapter : RecyclerView.Adapter<SearchListAdapter.SearchListHolder>() {
    private val placeList:MutableList<Place> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_result_item, parent, false)
        return SearchListHolder(view)
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    override fun onBindViewHolder(holder: SearchListHolder, position: Int) {
        holder.bind(placeList[position])
    }

    fun addPlaces(list: List<Place>){
        for(place in list){
            placeList.add(place)
        }
    }

    inner class SearchListHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun bind(result : Place){
            view.place_name_view.text = result.place_name
            view.address_view.text = result.address_name

            result.distance?.let {
                if(it.isNotEmpty()){
                    view.distance_view.text = "${it}m"
                }
            }

            view.setOnClickListener {
                val intent = Intent(view.context, SearchWebViewActivity::class.java)
                intent.putExtra("url", result.place_url)
                view.context.startActivity(intent)
            }
        }
    }
}