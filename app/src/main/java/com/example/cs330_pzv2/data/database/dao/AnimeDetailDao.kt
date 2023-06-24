package com.example.cs330_pzv2.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.cs330_pzv2.domain.model.AnimeDetail
import kotlinx.coroutines.flow.Flow


@Dao
interface AnimeDetailDao {

    @Upsert
    suspend fun insertAnimeDetail(animeDetail: AnimeDetail?)

    @Delete
    suspend fun deleteAnimeDetail(animeDetail: AnimeDetail)

    @Query("SELECT * FROM animedetail")
    fun getAnimeDetail(): Flow<List<AnimeDetail>>

    @Query("SELECT * FROM animedetail ORDER BY title ASC")
    fun getAnimeDetailSortedByTitle(): Flow<List<AnimeDetail>>

    @Query("SELECT * FROM animedetail ORDER BY id ASC")
    fun getAnimeDetailSortedById(): Flow<List<AnimeDetail>>

    @Query("SELECT * FROM animedetail WHERE watched=1")
    fun getWatchedAnimeDetails(): Flow<List<AnimeDetail>>

    @Query("SELECT * FROM animedetail WHERE watched=0")
    fun getNotWatchedAnimeDetails(): Flow<List<AnimeDetail>>

}