package com.example.cs330_pzv2.domain.use_case.database_use_cases

import com.example.cs330_pzv2.domain.model.AnimeDetail
import com.example.cs330_pzv2.domain.repository.AnimeDatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAnimeDatabaseRepository : AnimeDatabaseRepository {

    private val animeList = mutableListOf<AnimeDetail>()

    override suspend fun insertNewAnime(animeDetail: AnimeDetail) {
        animeList.add(animeDetail)
    }

    override fun getAllAnimeDetail(): Flow<List<AnimeDetail>> {
        return flow { emit(animeList) }
    }

    override fun getAnimeDetailSortedByTitle(): Flow<List<AnimeDetail>> {
        val sortedList = animeList.sortedBy { it.title }
        return flow { emit(sortedList) }
    }

    override suspend fun deleteAnimeDetail(animeDetail: AnimeDetail) {
        animeList.remove(animeDetail)
    }

    override fun getWatchedAnimeDetails(): Flow<List<AnimeDetail>> {
        val watchedList = animeList.filter { it.watched }
        return flow { emit(watchedList) }
    }

    override fun getNotWatchedAnimeDetails(): Flow<List<AnimeDetail>> {
        val notWatchedList = animeList.filter { !it.watched }
        return flow { emit(notWatchedList) }
    }

    override fun getAnimeFromDatabaseById(animeId: Int): Flow<AnimeDetail> {
        val anime = animeList.find { it.id == animeId }
        return flow { anime?.let { emit(it) } }
    }

    override suspend fun deleteAnimeFromDatabaseById(animeId: Int) {
        val anime = animeList.find { it.id == animeId }
        anime?.let { animeList.remove(it) }
    }

    fun setAnimeList(animeList: List<AnimeDetail>) {
        this.animeList.clear()
        this.animeList.addAll(animeList)
    }
}
