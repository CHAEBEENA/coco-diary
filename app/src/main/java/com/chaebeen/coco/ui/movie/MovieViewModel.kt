package com.chaebeen.coco.ui.movie

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaebeen.coco.data.database.dao.MovieDao
import com.chaebeen.coco.data.database.model.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieDao: MovieDao
): ViewModel() {

    private val _movies = MutableLiveData<List<MovieEntity>>()
    val movies: LiveData<List<MovieEntity>>
        get() = _movies

    init {
        _movies.postValue(getAll().value)
    }

    fun getAll() : LiveData<List<MovieEntity>>{
        return movieDao.getAll()
    }

    fun insert(movieItem: MovieEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDao.insert(movieItem)
        }
    }

}
