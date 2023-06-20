package com.example.cs330_pzv2.data.database

import com.example.cs330_pzv2.domain.model.AnimeDetail

sealed interface AnimeDetailEvent {

    object SaveAnimeDetail: AnimeDetailEvent
    data class DeleteAnimeDetail(val animeDetail: AnimeDetail): AnimeDetailEvent
}