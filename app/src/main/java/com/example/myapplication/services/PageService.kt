package com.example.myapplication.services


import com.example.myapplication.data.remote.MovieAPI
import com.example.myapplication.data.remote.MovieAPIClient
import com.example.myapplication.data.repositories.MovieRepository
import com.example.myapplication.data.repositories.PageRepository
import com.example.myapplication.data.viewModel.MovieDetailsViewModel
import com.example.myapplication.data.viewModel.PageViewModel

class PageService(private val movieAPI: MovieAPI)  {

//    fun getPage(page: Int): PageViewModel {
//        val movieAPIClient = MovieAPIClient()
//        val movieAPI = movieAPIClient.getClient()
//        val pageRepository = PageRepository(movieAPI)
//
//        return PageViewModel(pageRepository, page)
//    }
}