package com.fsocualaya.androidtv_assignment

import android.os.Bundle
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {
    private var searchView : SearchView? = null
    private var queryTV: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView = findViewById(R.id.search_view)
        queryTV = findViewById(R.id.query_tv)

        handleSearchView()
    }

    private fun handleSearchView(){
        searchView!!.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                queryTV!!.text = "Search results for \"$query\""
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })


    }
}