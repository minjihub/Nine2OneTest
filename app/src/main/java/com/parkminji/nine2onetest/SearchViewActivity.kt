package com.parkminji.nine2onetest

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parkminji.nine2onetest.adapter.SearchListAdapter
import com.parkminji.nine2onetest.search.Contract
import com.parkminji.nine2onetest.search.Presenter
import com.parkminji.nine2onetest.utils.GpsService
import kotlinx.android.synthetic.main.activity_search_view.*

class SearchViewActivity : AppCompatActivity(), Contract.View {
    private var presenter: Contract.Presenter? = null
    private val PERMISSION_REQUEST_CODE = 100
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
    private var page = 1
    private var limit = 10
    private var permissionResult = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)
        permissionCheck()
        presenter = Presenter(this)

        search_btn.setOnClickListener {
            search_edit.text?.let {
                progress_bar.visibility = View.VISIBLE
                page = 1
                presenter?.getSearchData(it.toString(),page, limit, longitude, latitude)
            }
        }

        search_edit.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                Handler().postDelayed({
                    search_btn.callOnClick()
                }, 500)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    override fun onResume() {
        super.onResume()
        if(permissionResult){
            GpsService().getLocation(this){
                longitude = it.longitude
                latitude = it.latitude
            }
        }
    }

    override fun successSearch() {
        presenter?.getAdapter()
    }

    override fun failSearch() {
        Toast.makeText(this, "네트워크를 확인해주세요.", Toast.LENGTH_SHORT).show()
    }

    override fun setAdapter(adapter: SearchListAdapter) {
        progress_bar.visibility = View.GONE
        search_list_view.adapter = adapter
        search_list_view.layoutManager = LinearLayoutManager(this)

        search_list_view.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastCompletelyVisibleItemPosition()
                val totalCount = adapter.itemCount - 1

                if(lastPosition == totalCount){
                    progress_bar.visibility = View.VISIBLE
                    page += 1
                    presenter?.getSearchData(search_edit.text.toString(),page, limit, longitude, latitude)
                }
            }
        })
    }

    override fun settingRecyclerView() {
        progress_bar.visibility = View.GONE
        search_list_view.adapter?.notifyDataSetChanged()
    }

    override fun finishSearch() {
        progress_bar.visibility = View.GONE
    }

    private fun permissionCheck(){
        val locationPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            applicationContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            PackageManager.PERMISSION_GRANTED
        }

        if(locationPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSION_REQUEST_CODE
            )
        }else{
            permissionResult = true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            PERMISSION_REQUEST_CODE ->{
                permissionResult = grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                return
            }
        }
    }
}