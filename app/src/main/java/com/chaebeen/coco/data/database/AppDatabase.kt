package com.chaebeen.coco.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chaebeen.coco.data.database.dao.MovieDao
import com.chaebeen.coco.data.database.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
