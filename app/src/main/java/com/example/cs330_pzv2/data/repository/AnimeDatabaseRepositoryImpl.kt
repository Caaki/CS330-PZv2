package com.example.cs330_pzv2.data.repository

import com.example.cs330_pzv2.data.database.dao.AnimeDetailDao
import com.example.cs330_pzv2.domain.model.AnimeDetail
import com.example.cs330_pzv2.domain.repository.AnimeDatabaseRepository
import kotlinx.coroutines.flow.Flow

class AnimeDatabaseRepositoryImpl(
    private val dao : AnimeDetailDao
) :AnimeDatabaseRepository{
    override suspend fun insertNewAnime(animeDetail: AnimeDetail) {
        dao.insertAnimeDetail(animeDetail)
    }

    override fun getAllAnimeDetail(): Flow<List<AnimeDetail>> {
        return dao.getAnimeDetail()
    }

    override fun getAnimeDetailSortedByTitle(): Flow<List<AnimeDetail>> {
        return dao.getAnimeDetailSortedByTitle()
    }

    override fun getAnimeDetailSortedById(): Flow<List<AnimeDetail>> {
        return dao.getAnimeDetailSortedById()
    }

    override suspend fun deleteAnimeDetail(animeDetail: AnimeDetail) {
        dao.deleteAnimeDetail(animeDetail)
    }
}