package com.example.cs330_pzv2.domain.repository

import com.example.cs330_pzv2.data.remote.dto.AnimeDto

interface AnimeRepository {

    suspend fun getAllAnime(): List<AnimeDto>

    suspend fun getAnimeById(animeId: Int): AnimeDto

    suspend fun getAnimeByTitle(title: String): List<AnimeDto>

    suspend fun getAnimeByTag(tag: String): List<AnimeDto>


}