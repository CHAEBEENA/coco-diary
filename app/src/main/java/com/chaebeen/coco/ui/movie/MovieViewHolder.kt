package com.chaebeen.coco.ui.movie

import android.util.Log
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.chaebeen.coco.data.database.model.MovieEntity
import com.chaebeen.coco.databinding.ItemMovieBinding

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val viewModel: MovieViewModel,
    private val glide: RequestManager
) :RecyclerView.ViewHolder(binding.root){

    fun bind(item: MovieEntity) {
        binding.movieitemTitle.text = item.title
        //binding.movieitemImage.setImageURI(item.posterUrl)
        glide.load(item.posterUrl).apply(RequestOptions().centerCrop()).into(binding.movieitemImage)
        binding.movieitemRootLayout.setOnClickListener {
            viewModel.onMovieItemClick(item.id)
        }
    }

}