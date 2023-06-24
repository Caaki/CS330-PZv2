package com.example.cs330_pzv2.presentation

sealed class Screen (val route: String){

    object AnimeMainPage: Screen("anime_main_page")
    object AnimeDetailsScreen: Screen("anime_detail_screen")
    object AnimeDatabaseDetailScreen: Screen("anime_database_detail_screen")

}