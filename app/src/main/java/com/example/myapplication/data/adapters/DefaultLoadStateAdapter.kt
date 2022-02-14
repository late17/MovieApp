package com.example.myapplication.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R


class DefaultLoadStateAdapter(private val context : Context) : LoadStateAdapter<DefaultLoadStateAdapter.Holder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        val itemView = layoutInflater.inflate(R.layout.load_state, parent, false)
        return Holder(itemView)
    }

    /**
     * The same layout is used for:
     * - footer
     * - main indicator
     */
    class Holder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private var messageTextView = itemView.findViewById<TextView>(R.id.messageTextView)
        private var progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)


        fun bind(loadState: LoadState) {
            messageTextView.isVisible = loadState is LoadState.Error
            progressBar.isVisible = loadState is LoadState.Loading
        }
    }

}