package com.campus.disneycompose.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.campus.disneycompose.model.Poster

@Database(entities = [Poster::class], version = 1, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {

    abstract fun posterDao(): PosterDao
}
