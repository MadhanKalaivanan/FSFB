package com.fsfb.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "StoredData")
data class StoredData(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var email: String?,
    var isAccept: Boolean,
    var isDeclined: Boolean
)
