package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName


data class PageModel(
    val page: Int,
    @SerializedName("results")
    val movieList: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)