package com.example.testtecnicojairo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.testtecnicojairo.db.entity.SessionEntity

@Dao
interface SessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sessionEntity: SessionEntity):Long

    @Query("SELECT * FROM Session LIMIT 1")
    fun getAllSession(): LiveData<SessionEntity>

    @Delete
    suspend fun delete(articleEntity: SessionEntity)
}