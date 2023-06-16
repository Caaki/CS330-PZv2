package com.example.cs330_pzv2.domain.model

import com.example.cs330_pzv2.data.remote.dto.AnimeSeason

data class AnimeDetail (
    val year: Int,
    val episodes: Int,
    val picture: String,
    val status: String,
    val tags: List<String>,
    val title: String,
    val id: Int
)