package com.example.cs330_pzv2.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.cs330_pzv2.data.database.converters.StringListTypeConverter
import com.example.cs330_pzv2.data.remote.dto.AnimeSeason

@Entity
data class AnimeDetail(
    val year: Int,
    val episodes: Int,
    val picture: String,
    val status: String,
    @TypeConverters(StringListTypeConverter::class)
    val tags: List<String> = emptyList(),
    val title: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int = -1
)