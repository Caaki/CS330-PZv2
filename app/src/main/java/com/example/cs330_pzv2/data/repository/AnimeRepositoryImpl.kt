package com.example.cs330_pzv2.data.repository

import com.example.cs330_pzv2.data.remote.dto.AnimeDto
import com.example.cs330_pzv2.data.remote.AnimeApi
import com.example.cs330_pzv2.domain.repository.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val api : AnimeApi
) : AnimeRepository{
    override suspend fun getAllAnime(): List<AnimeDto> {
        return api.getAll()
    }

    override suspend fun getAnimeById(animeId: Int): AnimeDto {
        return api.getAnimeById(animeId)
    }

    override suspend fun getAnimeByTitle(title: String): List<AnimeDto> {
        return api.getAnimeByTitle(title)
   }

    override suspend fun getAnimeByTag(tag: String): List<AnimeDto> {
        return api.getAnimeByTag(tag)
    }

    override suspend fun getAnimeByTagMainPage(tag: String): List<AnimeDto> {
        return api.getAnimeByTagMainPage(tag)
    }
}