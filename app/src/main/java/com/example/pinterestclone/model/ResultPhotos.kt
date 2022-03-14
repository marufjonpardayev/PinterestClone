package com.example.pinterestclone.model

data class ResultPhotos(
    var total: Int? = null,
    var total_pages: Int? = null,
    var results: ArrayList<WelcomeElement>? = null
)