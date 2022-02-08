package com.example.myapplication.data.adapters

import android.view.LayoutInflater
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
        val context = parent.context
        val layoutId = R.layout.fragment_movie

        val layoutInflater = LayoutInflater.from(context)

        val view = layoutInflater.inflate(layoutId, parent, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind("title $position")
    }

    override fun getItemCount(): Int {
        return 100
    }

    class MovieViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var title = itemView.findViewById<TextView>(R.id.main_movie_title)
        private var genres = itemView.findViewById<TextView>(R.id.main_movie_genres)
        private var poster = itemView.findViewById<ImageView>(R.id.main_movie_poster)

        fun bind(title : String) {
            this.title.text = title
        }
    }

}