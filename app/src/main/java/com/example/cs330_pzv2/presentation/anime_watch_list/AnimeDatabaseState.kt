package com.example.cs330_pzv2.presentation.anime_watch_list

import com.example.cs330_pzv2.domain.model.AnimeDetail
import kotlinx.coroutines.flow.Flow

data class AnimeDatabaseState(

    val animeDetail:List<AnimeDetail> = emptyList(),
    val animeDetailFinished:List<AnimeDetail> = emptyList()

)

