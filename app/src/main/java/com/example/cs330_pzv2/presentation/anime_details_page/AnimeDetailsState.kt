package com.example.cs330_pzv2.presentation.anime_details_page

import com.example.cs330_pzv2.domain.model.AnimeDetail

data class AnimeDetailsState(

    val isLoading: Boolean = false,
    val anime: AnimeDetail? =null,
    val error: String = "",


)
