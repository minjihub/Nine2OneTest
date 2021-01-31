package com.parkminji.nine2onetest.search

import android.util.Log
import com.parkminji.nine2onetest.adapter.SearchListAdapter
import com.parkminji.nine2onetest.model.Place
import com.parkminji.nine2onetest.model.PlaceData
import com.parkminji.nine2onetest.utils.RetrofitConnect
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Model(val requiredPresenter: Contract.RequiredPresenter) {
    private var searchResultList: List<Place>? = null
    private val API_KEY = "생성한 REST API 키"
    private lateinit var adapter: SearchListAdapter

    fun getSearchData(keyword: String, page: Int, size: Int, x: Double, y: Double){
        val service = RetrofitConnect().searchDataService
        val call = service?.getSearchData(API_KEY, keyword, page, size, x, y)
        call?.enqueue(object : Callback<PlaceData>{
            override fun onFailure(call: Call<PlaceData>, t: Throwable) {
                Log.e("Retrofit_fail", t.toString())
                requiredPresenter.failSearch()
            }

            override fun onResponse(call: Call<PlaceData>, response: Response<PlaceData>) {
                Log.e("Retrofit_success", response.body().toString())
                val result = response.body()?.documents

                if(result == null){
                    requiredPresenter.finishSearch()
                }

                result?.let {
                    if(page == 1){
                        searchResultList = it
                        requiredPresenter.successSearch()
                    }else{
                        notifyList(it)
                        requiredPresenter.settingRecyclerView()
                    }
                }
            }
        })
    }

    fun getAdapter(){
        searchResultList?.let {
            adapter = SearchListAdapter()
            adapter.addPlaces(it)
            requiredPresenter.setAdapter(adapter)
        }
    }

    fun notifyList(list: List<Place>){
        adapter.addPlaces(list)
    }
}