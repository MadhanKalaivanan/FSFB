package com.fsfb.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RoomDao {
    @Insert
    fun insertData(storedData: StoredData)

    @Update
    fun updateData(storedData: StoredData)

    @Query("SELECT * FROM StoredData WHERE email =:email")
    fun getData(email: String): StoredData?

    @Query("SELECT * FROM StoredData")
    fun getAllData(): List<StoredData>?
}