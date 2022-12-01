package com.example.testtecnicojairo.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Session")
data class SessionEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
        val success: Boolean?,
        val session_id: String?
    )
