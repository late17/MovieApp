package com.example.myapplication.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.Movie

class MyPageAdapter(context : Context) : RecyclerView.Adapter<MyPageAdapter.MovieViewHolder>() {

    private var numberItems = 100
    private var page = 1
    private var movieSource = MovieSource()
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflate = layoutInflater.inflate(R.layout.fragment_movie, parent, false)
        return MovieViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieSource.getMovie(position))
        numberItems++
    }

    override fun getItemCount(): Int {
        return numberItems
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var title = itemView.findViewById<TextView>(R.id.main_movie_title)
        private var genres = itemView.findViewById<TextView>(R.id.main_movie_genres)
        private var poster = itemView.findViewById<ImageView>(R.id.main_movie_poster)

        fun bind(movie : Movie){
            title = itemView.findViewById(R.id.main_movie_title)
            title.text = movie.title
        }
    }

}