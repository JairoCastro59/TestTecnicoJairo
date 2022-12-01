package com.example.testtecnicojairo.db.converters

import androidx.room.TypeConverter
import com.example.testtecnicojairo.db.entity.SourceEntitty
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    val gson = Gson()

    @TypeConverter
    fun toSourceEntityToString(sourceEntity: SourceEntitty): String {

        val type = object : TypeToken<SourceEntitty>() {}.type
        return gson.toJson(sourceEntity, type)
    }

    @TypeConverter
    fun fromStringToSourceEntity(string: String): SourceEntitty {

        val type = object : TypeToken<SourceEntitty>() {}.type
        return gson.fromJson<SourceEntitty>(string, type)

    }
}