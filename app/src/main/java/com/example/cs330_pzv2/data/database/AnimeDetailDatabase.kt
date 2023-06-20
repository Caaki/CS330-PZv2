package com.example.cs330_pzv2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cs330_pzv2.data.database.converters.StringListTypeConverter
import com.example.cs330_pzv2.data.database.dao.AnimeDetailDao
import com.example.cs330_pzv2.domain.model.AnimeDetail

@Database(
    entities = [AnimeDetail::class],
    version = 1
)
@TypeConverters(StringListTypeConverter::class)
abstract class AnimeDetailDatabase :RoomDatabase(){

    abstract val dao: AnimeDetailDao

}