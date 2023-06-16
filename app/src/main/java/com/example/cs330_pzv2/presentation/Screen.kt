package com.example.cs330_pzv2.presentation

sealed class Screen (val route: String){

    object AnimeMainPage: Screen("anime_main_page")

}