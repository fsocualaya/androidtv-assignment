package com.fsocualaya.androidtv_assignment

import android.os.StrictMode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

class FlickrService {
    var client = OkHttpClient()
    var BASE_URL = "https://api.flickr.com/services/rest/"

    private fun buildURL(
        method: String,
        text: String?,
        per_page: Int = 6,
        page: Int
    ): HttpUrl {
        val urlBuilder = HttpUrl.parse(BASE_URL)?.newBuilder()
        urlBuilder!!.addQueryParameter("method", method)
        urlBuilder.addQueryParameter("api_key", "a8287085cc11cd73bd8bfb15b3ca65b8")
        urlBuilder.addQueryParameter("format", "json")
        urlBuilder.addQueryParameter("text", text)
        urlBuilder.addQueryParameter("nojsoncallback", "1")
        urlBuilder.addQueryParameter("per_page", "6")
        urlBuilder.addQueryParameter("page", page.toString())
        urlBuilder.addQueryParameter("extras", "date_taken,owner_name")

        return urlBuilder.build()
    }

    fun getPhotosByTag(query:String, page:Int):ArrayList<Photo>{
        // Disabled StrictMode to test requests
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX)
        val request = Request.Builder().url(buildURL(
            method="flickr.photos.search",
            text=query,
            page=page,
        )).build()

        val photoList : ArrayList<Photo> = arrayListOf()
        client.newCall(request).execute().use { response ->
            if (response.isSuccessful) {
                val mapper = ObjectMapper().registerKotlinModule()
                val root = mapper.readTree(response.body()?.string())
                val photos = root.get("photos").get("photo")

                for(photo in photos){
                    photoList.add(Photo(
                        photo.get("ownername").textValue(),
                        photo.get("datetaken").textValue(),
                        photo.get("title").textValue(),
                        photo.get("server").textValue(),
                        photo.get("id").textValue(),
                        photo.get("secret").textValue(),
                    ))
                }
                return photoList

            } else {
            }
        }
        return photoList
    }

    fun getRecentPhotos(){

    }
}