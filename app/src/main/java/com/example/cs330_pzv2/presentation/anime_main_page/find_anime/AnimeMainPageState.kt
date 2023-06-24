package com.example.cs330_pzv2.presentation.anime_main_page.find_anime

import com.example.cs330_pzv2.domain.model.Anime

data class AnimeMainPageState(

    val isLoading: Boolean = false,
    val error: String = "",
    val action_anime: List<Anime> = emptyList(),
    val mystery_anime: List<Anime> = emptyList(),
    val romance_anime: List<Anime> = emptyList(),
    val yandere_anime: List<Anime> = emptyList(),
    val isekai_anime: List<Anime> = emptyList(),
    val search_anime: List<Anime> = emptyList(),
    val searchString:String = "",
    val searchTags:String = "",
    val searchCount:Int =0


)
