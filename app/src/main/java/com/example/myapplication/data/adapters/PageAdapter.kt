package com.example.myapplication.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.Movie

class PageAdapter(context: Context) : PagingDataAdapter<Movie, PageAdapter.MovieViewHolder>(ArticleDiffItemCallback)  {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(layoutInflater.inflate(R.layout.fragment_movie, parent, false))
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var title = itemView.findViewById<TextView>(R.id.main_movie_title)

        fun bind(movie : Movie?){
            title.text = movie?.title
        }
    }
}

private object ArticleDiffItemCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.title == newItem.title && oldItem.poster_path == newItem.poster_path
    }
}