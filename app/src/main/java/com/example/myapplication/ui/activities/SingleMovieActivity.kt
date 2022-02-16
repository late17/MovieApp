package com.example.myapplication.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView

import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.model.MovieDetails
import com.example.myapplication.data.remote.POSTER_BASE_URL
import com.example.myapplication.data.viewModel.MovieDetailsViewModel
import com.example.myapplication.presenters.SingleMoviePresenter
import com.example.myapplication.data.repositories.NetworkState.Companion.LOADED
import com.example.myapplication.views.SingleMovieView
import java.text.NumberFormat
import java.util.*

class SingleMovieActivity : AppCompatActivity(), SingleMovieView {
    private lateinit var presenter : SingleMoviePresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)

        val movieId: Int = intent.getIntExtra("movieId",1)
        presenter = SingleMoviePresenter(this)
        presenter.onCreate(movieId)


    }

    override fun setData(movieDetails: MovieDetailsViewModel){
        movieDetails.networkState.observe(this, Observer {
            val pBar = findViewById<ProgressBar>(R.id.progressBar)
            val content = findViewById<ScrollView>(R.id.Movie)
            content.visibility = if (it == LOADED) View.VISIBLE else View.GONE
            pBar.visibility = if (it == LOADED) View.GONE else View.VISIBLE
        })
        movieDetails.movieDetails.observe(this, Observer {
            bindData(it)
        })
    }


    @SuppressLint("SetTextI18n")
    fun bindData(movieDetails: MovieDetails) {
        findViewById<TextView>(R.id.movie_title).text = movieDetails.title
        findViewById<TextView>(R.id.movie_tagline).text = movieDetails.tagline
        findViewById<TextView>(R.id.movie_release_date).text = movieDetails.releaseDate
        findViewById<TextView>(R.id.movie_rating).text = movieDetails.rating.toString()
        findViewById<TextView>(R.id.movie_runtime).text = movieDetails.runtime.toString() + " minutes"
        findViewById<TextView>(R.id.movie_overview).text = movieDetails.overview

        findViewById<TextView>(R.id.movie_genres).text =
            movieDetails.genres.map { it.name }.toString()//tut

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        findViewById<TextView>(R.id.movie_budget).text = formatCurrency.format(movieDetails.budget)
        findViewById<TextView>(R.id.movie_revenue).text = formatCurrency.format(movieDetails.revenue)

        val moviePosterURL = POSTER_BASE_URL + movieDetails.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(findViewById<ImageView>(R.id.iv_movie_poster))
    }


}