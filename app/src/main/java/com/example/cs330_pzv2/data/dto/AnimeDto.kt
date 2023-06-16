package com.example.cs330_pzv2.data.dto

data class AnimeDto(
    val animeSeason: AnimeSeason,
    val episodes: Int,
    val picture: String,
    val relations: List<String>,
    val sources: List<String>,
    val status: String,
    val synonyms: List<String>,
    val tags: List<String>,
    val thumbnail: String,
    val title: String,
    val type: String,
    val id: Int
)