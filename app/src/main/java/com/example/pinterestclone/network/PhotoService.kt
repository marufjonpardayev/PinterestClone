package com.example.pinterestclone.network


import com.example.pinterestclone.model.RelatedPhotos
import com.example.pinterestclone.model.ResultPhotos
import com.example.pinterestclone.model.Welcome
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
interface PhotoService {

    companion object {
        private const val ACCESS_KEY = "AGB0HEbdk2MT9L1eLae4sMz8QKLwDBUTmbvE5flUn3I"
        const val client_id = "Client-ID"
    }

    @Headers("Authorization:$client_id $ACCESS_KEY")

    @GET("photos")
    fun getPhotos(@Query("page") page: Int, @Query("per_page") perPage: Int): Call<Welcome>

    @Headers("Authorization:$client_id $ACCESS_KEY")
    @GET("photos/{id}/related")
    fun getRelatedPhotos(@Path("id") id: String): Call<RelatedPhotos>


    @Headers("Authorization:$client_id $ACCESS_KEY")
    @GET("search/photos")
    fun getSearchPhoto(
        @Query("page") page: Int,
        @Query("query") query: String,
        @Query("per_page") perPage: Int
    ): Call<    ResultPhotos>




}