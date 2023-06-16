package com.example.cs330_pzv2.domain.model

import com.example.cs330_pzv2.data.dto.AnimeSeason

data class AnimeDetail (
    val animeSeason: AnimeSeason,
    val episodes: Int,
    val picture: String,
    val relations: List<String>,
    val sources: List<String>,
    val status: String,
    val synonyms: List<String>,
    val tags: List<String>,
    val title: String,
    val type: String,
    val id: Int
)