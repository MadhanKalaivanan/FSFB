package com.fsfb.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StoredData::class],version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun roomDao(): RoomDao

}