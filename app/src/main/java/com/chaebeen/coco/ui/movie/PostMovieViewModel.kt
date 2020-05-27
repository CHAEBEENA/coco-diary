package com.chaebeen.coco.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaebeen.coco.data.database.dao.MovieDao
import com.chaebeen.coco.data.database.model.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostMovieViewModel(
    private val movieDao: MovieDao
) : ViewModel() {

    fun insertMovie(movieItem: MovieEntity){
        viewModelScope.launch(Dispatchers.IO) {
            movieDao.insert(movieItem)
        }
    }
}
