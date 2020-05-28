package com.chaebeen.coco.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chaebeen.coco.data.database.model.MovieEntity
import com.chaebeen.coco.databinding.ItemMovieBinding

class MovieRecyclerViewAdapter: RecyclerView.Adapter<MovieViewHolder>() {

    var items = listOf<MovieEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position])
    }

}


