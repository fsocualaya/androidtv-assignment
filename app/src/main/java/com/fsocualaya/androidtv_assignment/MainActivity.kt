package com.fsocualaya.androidtv_assignment

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.TextView
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {
    private var searchView : SearchView? = null
    private var queryTV: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setViews()
        setEventListeners()
    }

    private fun setViews(){
        searchView = findViewById(R.id.search_view)
        queryTV = findViewById(R.id.query_tv)
    }

    private fun setEventListeners(){
        searchView!!.setOnQueryTextListener(SearchViewListener())
    }
    private inner class SearchViewListener: OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {
            queryTV!!.text = "Search results for \"$query\""
            // TODO: send request and process results
            processQuery(query!!)
            return false
        }

        override fun onQueryTextChange(p0: String?): Boolean {
            return false
        }
    }

    private fun processQuery(query : String){
        val flickrService = FlickrService()
        val photoList = flickrService.getPhotosByTag(
            query=query,
            page=1
        )
        for(photo in photoList){

        }
    }
}