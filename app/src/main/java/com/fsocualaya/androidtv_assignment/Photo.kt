package com.fsocualaya.androidtv_assignment

import java.sql.Date
import java.text.SimpleDateFormat

data class Photo(
    val author: String,
    val date: String,
    val title: String,
    val serverId: String,
    val Id: String,
    val secret: String,
    val size: String = "w"
    ){

    var staticURL = "$BASE_URL/$serverId/$Id" + '_' + secret + '_' + size + EXTENSION

    fun genDate(): String? {
        return fmt.format(Date.valueOf(date.substring(0,10)))
    }
    companion object {
        private var BASE_URL = "https://live.staticflickr.com"
        private var EXTENSION = ".png"
        private var fmt:SimpleDateFormat = SimpleDateFormat("MMM dd yyyy")
    }
}