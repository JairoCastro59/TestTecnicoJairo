package com.example.testtecnicojairo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testtecnicojairo.db.converters.Converter
import com.example.testtecnicojairo.db.dao.SessionDao
import com.example.testtecnicojairo.db.entity.SessionEntity

@Database(
    version = 1,
    entities = [SessionEntity::class],
)
@TypeConverters(Converter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun sessionDao(): SessionDao

}