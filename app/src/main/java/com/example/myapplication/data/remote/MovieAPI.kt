package com.example.myapplication.data.remote

import com.example.myapplication.data.model.MovieDetails
import com.example.myapplication.data.model.PageModel
import io.reactivex.Single
import retrofit2.http.*

interface MovieAPI {


    @GET("movie/{movie_id}")
    fun getMovieDetailsById(@Path("movie_id") id: Int): Single<MovieDetails>

    @GET("movie/popular")
    suspend fun getPopular(@Query("page") pageNum: Int):PageModel

}