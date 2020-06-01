package com.chaebeen.coco.ui.movie

import android.app.usage.UsageEvents
import android.util.EventLog
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaebeen.coco.data.database.dao.MovieDao
import com.chaebeen.coco.data.database.model.MovieEntity
import com.chaebeen.coco.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieDao: MovieDao
): ViewModel() {

    private val _movies = MutableLiveData<List<MovieEntity>>()
    val movies: LiveData<List<MovieEntity>>
        get() = _movies

    private val _movieItem = MutableLiveData<MovieEntity>()
    val movieItem : LiveData<MovieEntity>
        get() = _movieItem

    private val _navigateToDetailMovieFragment = MutableLiveData<Event<Unit>>()
    val navigateToDetailMovieFragment : LiveData<Event<Unit>>
        get() = _navigateToDetailMovieFragment

    init {
        _movies.postValue(getAll().value)


    }

    fun getAll() : LiveData<List<MovieEntity>>{
        return movieDao.getAll()
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            movieDao.delete()
        }
    }

    fun delete(movie: MovieEntity){
        viewModelScope.launch(Dispatchers.IO) {
            movieDao.delete(movie)
        }
    }

    fun insert(movieItem: MovieEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDao.insert(movieItem)
        }
    }

    fun onMovieItemClick(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _movieItem.postValue(movieDao.select(movieId))
            _navigateToDetailMovieFragment.postValue(Event(Unit))
        }

    }

}
