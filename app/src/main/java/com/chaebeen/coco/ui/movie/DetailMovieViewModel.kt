package com.chaebeen.coco.ui.movie

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chaebeen.coco.data.database.model.MovieEntity

class DetailMovieViewModel : ViewModel() {
    private var _currentItem = MutableLiveData<MovieEntity>()
    val currentItem : LiveData<MovieEntity>
        get() = _currentItem

    fun getItem(item: MovieEntity){
        _currentItem.postValue(item)
    }
}
