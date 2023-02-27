package com.fsocualaya.androidtv_assignment

import java.sql.Date

data class Photo(
    val author: String,
    val date: String,
    val title: String,
    val serverId: String,
    val Id: String,
    val secret: String,
    val size: String = "w"
    ){

    var BASE_URL="https://live.staticflickr.com"
    var EXTENSION =".jpg"
    var staticURL = "$BASE_URL/$serverId/$Id"+ '_' + secret + '_' + size + EXTENSION
}