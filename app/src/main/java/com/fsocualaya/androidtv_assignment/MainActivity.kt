package com.fsocualaya.androidtv_assignment

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.TextView
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {
    private lateinit var searchView: SearchView
    private lateinit var queryTV: TextView
    private lateinit var resultsContainer: FrameLayout
    private lateinit var resultsFragment:ResultsFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViews()
        setEventListeners()
    }

    private fun setViews(){
        searchView = findViewById(R.id.search_view)
        queryTV = findViewById(R.id.query_tv)
        resultsContainer = findViewById(R.id.results_fragment)

        resultsFragment = ResultsFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(resultsContainer.id, resultsFragment)
        transaction.commit()
    }

    private fun setEventListeners(){
        searchView.setOnQueryTextListener(SearchViewListener())
    }
    private inner class SearchViewListener: OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {
            queryTV.text = "Search results for \"$query\""
            resultsFragment.reloadPhotos(query)
            searchView.clearFocus()
            return false
        }

        override fun onQueryTextChange(p0: String?): Boolean {
            return false
        }
    }
}