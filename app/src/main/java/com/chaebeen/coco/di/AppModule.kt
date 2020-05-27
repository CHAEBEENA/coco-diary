package com.chaebeen.coco.di

import androidx.room.Room
import com.chaebeen.coco.data.database.AppDatabase
import com.chaebeen.coco.ui.movie.MovieViewModel
import com.chaebeen.coco.ui.movie.PostMovieViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "haema.db")
            .build()
    }

    single { get<AppDatabase>().movieDao() }

    viewModel { MovieViewModel(get()) }

    viewModel { PostMovieViewModel(get()) }
}
