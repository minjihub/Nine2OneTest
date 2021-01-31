package com.parkminji.nine2onetest.search

import com.parkminji.nine2onetest.adapter.SearchListAdapter

class Presenter(private val view: Contract.View) : Contract.Presenter, Contract.RequiredPresenter {
    var model: Model? = null

    init {
        model = Model(this)
    }

    override fun getSearchData(keyword: String, page: Int, size: Int, x: Double, y: Double) {
        model?.getSearchData(keyword, page, size, x, y)
    }

    override fun getAdapter() {
        model?.getAdapter()
    }

    override fun successSearch() {
        view.successSearch()
    }

    override fun failSearch() {
        view.failSearch()
    }

    override fun setAdapter(adapter: SearchListAdapter) {
        view.setAdapter(adapter)
    }

    override fun settingRecyclerView() {
        view.settingRecyclerView()
    }

    override fun finishSearch() {
        view.finishSearch()
    }
}