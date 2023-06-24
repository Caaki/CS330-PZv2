package com.example.cs330_pzv2.domain.repository

import com.example.cs330_pzv2.domain.model.AnimeDetail
import kotlinx.coroutines.flow.Flow

interface AnimeDatabaseRepository {

    suspend fun insertNewAnime(animeDetail: AnimeDetail)

    fun getAllAnimeDetail(): Flow<List<AnimeDetail>>

    fun getAnimeDetailSortedByTitle(): Flow<List<AnimeDetail>>

    fun getAnimeDetailSortedById(): Flow<List<AnimeDetail>>

    suspend fun deleteAnimeDetail(animeDetail: AnimeDetail)


}