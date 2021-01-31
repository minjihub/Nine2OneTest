package com.parkminji.nine2onetest.search

import com.parkminji.nine2onetest.adapter.SearchListAdapter
import com.parkminji.nine2onetest.model.Place

interface Contract {

    interface View {
        fun successSearch()
        fun failSearch()
        fun setAdapter(adapter: SearchListAdapter)
        fun settingRecyclerView()
        fun finishSearch()

    }

    interface Presenter {
        fun getSearchData(keyword: String, page: Int, size: Int, x: Double, y: Double)
        fun getAdapter()
    }

    interface RequiredPresenter {
        fun successSearch()
        fun failSearch()
        fun setAdapter(adapter: SearchListAdapter)
        fun settingRecyclerView()
        fun finishSearch()
    }

}