package com.chaebeen.coco.ui.movie

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chaebeen.coco.data.database.model.MovieEntity
import com.chaebeen.coco.databinding.ItemMovieBinding

class MovieViewHolder(
    private val binding: ItemMovieBinding
) :RecyclerView.ViewHolder(binding.root){

    fun bind(item: MovieEntity) {
        binding.movieitemTitle.text = item.title
        //binding.movieitemImage.setImageURI(item.posterUrl)
        Glide.with(binding.root).load(item.posterUrl).apply(RequestOptions().centerCrop()).into(binding.movieitemImage)
    }

}