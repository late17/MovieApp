package com.example.myapplication.data.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class PageAdapter : RecyclerView.Adapter<PageAdapter.MovieViewHolder>() {

    private var numberItems = 0
    private var page = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class MovieViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var title = itemView.findViewById<TextView>(R.id.main_movie_title)
        private var genres = itemView.findViewById<TextView>(R.id.main_movie_genres)
        private var poster = itemView.findViewById<ImageView>(R.id.main_movie_poster)
    }

}