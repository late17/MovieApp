package com.example.myapplication.data.adapters

import com.example.myapplication.data.model.Movie

class MovieSource {

    fun getMovie(position: Int): Movie {
        val genres: List<Int> = listOf(2, 3)
        return Movie(genres, "", "2022", "title $position")
    }
}