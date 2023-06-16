package com.example.cs330_pzv2.data.remote

import com.example.cs330_pzv2.data.remote.dto.AnimeDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApi {

    @GET("/data")
    suspend fun getAll(): List<AnimeDto>

    @GET("/data/{animeId}")
    suspend fun getAnimeById(@Path("animeId") animeId: Int): AnimeDto

    @GET("/data")
    suspend fun getAnimeByTitle(
        @Query("title_like") title: String,
        @Query("_page") page: Int = 1,
        @Query("title_regex_flags") flags: String = "i"
    ):List<AnimeDto>

    @GET("/data")
    suspend fun getAnimeByTag(
        @Query("tags_like") tag: String,
        @Query("_page") page: Int = 1,
        @Query("title_regex_flags") flags: String = "i"
    ):List<AnimeDto>




}