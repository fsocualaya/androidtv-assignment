package com.fsocualaya.androidtv_assignment

class QueryManager {
    private val flickrService: FlickrService = FlickrService()
    private var currentQuery: String? = null
    private var pageIterator: Int=1

    fun reloadResults(query:String?):ArrayList<Photo>{
        currentQuery = query
        pageIterator = 1
        if (currentQuery==null){
            return flickrService.getRecentPhotos(1)
        }
        return flickrService.getPhotosByTag(currentQuery!!, 1)
    }

    fun updateResults(): ArrayList<Photo>{
        pageIterator++
        if(currentQuery == null){
            return flickrService.getRecentPhotos(pageIterator)
        }
        return flickrService.getPhotosByTag(currentQuery!!, pageIterator)
    }
    companion object{
        private val instance = QueryManager()

        fun getInstance():QueryManager{
            return instance
        }
    }
}