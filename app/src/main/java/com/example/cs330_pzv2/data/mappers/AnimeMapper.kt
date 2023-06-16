package com.example.cs330_pzv2.data.mappers

import com.example.cs330_pzv2.data.remote.dto.AnimeDto
import com.example.cs330_pzv2.domain.model.Anime
import com.example.cs330_pzv2.domain.model.AnimeDetail

fun AnimeDto.toAnime(): Anime {
    return Anime(
        id = id,
        title = title,
        episodes = episodes,
        picture = picture,
        tags = tags
    )
}

fun AnimeDto.toAnimeDetail(): AnimeDetail {
    return AnimeDetail(
        id = id,
        tags = tags,
        title = title,
        picture = picture,
        year = animeSeason.year,
        episodes = episodes,
        status = status,
    )
}