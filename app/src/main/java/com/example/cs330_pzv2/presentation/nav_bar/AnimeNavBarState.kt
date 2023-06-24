package com.example.cs330_pzv2.presentation.nav_bar

data class AnimeNavBarState(

    val isMainPage: Boolean = true,
    val isSearching: Boolean = false,
    val localDatabase:Boolean = false,
    val dialog: Boolean = false,
    val isWatched :Boolean = false
)