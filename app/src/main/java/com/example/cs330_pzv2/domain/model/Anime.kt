package com.example.cs330_pzv2.domain.model

import com.example.cs330_pzv2.data.dto.AnimeSeason

data class Anime(
    val episodes: Int,
    val tags: List<String>,
    val title: String,
    val picture: String,
    val id:Int
)
