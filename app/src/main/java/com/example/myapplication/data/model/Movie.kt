package com.example.myapplication.data.model

data class Movie(
    val genre_ids: List<Int>,
    val poster_path: String,
    val release_date: String,
    val title: String
)