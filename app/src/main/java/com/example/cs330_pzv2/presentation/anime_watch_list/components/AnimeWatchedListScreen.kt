package com.example.cs330_pzv2.presentation.anime_watch_list.components

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cs330_pzv2.presentation.anime_watch_list.AnimeDatabaseViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AnimeWatchedListScreen (
    navController: NavController,
    viewModel: AnimeDatabaseViewModel = hiltViewModel()
){

    AnimeDetailMainItemList(animeList = viewModel.state.value.animeDetail, title = "Watched Anime", navController = navController)

}
