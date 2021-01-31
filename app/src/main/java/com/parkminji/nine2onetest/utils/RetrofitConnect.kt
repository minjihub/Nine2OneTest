package com.parkminji.nine2onetest.utils

import com.parkminji.nine2onetest.model.PlaceData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

class RetrofitConnect {
    private var retrofit: Retrofit? = null
    var searchDataService: SearchDataService? = null

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        searchDataService = retrofit?.create(SearchDataService::class.java)
    }

    interface SearchDataService{
        @GET("v2/local/search/keyword.json")
        fun getSearchData(
                @Header("Authorization") key: String,
                @Query("query") query: String,
                @Query("page") page: Int,
                @Query("size") size: Int,
                @Query("x") x: Double,
                @Query("y") y: Double,
                @Query("sort") sort: String = "distance"
        ) : Call<PlaceData>

    }

}