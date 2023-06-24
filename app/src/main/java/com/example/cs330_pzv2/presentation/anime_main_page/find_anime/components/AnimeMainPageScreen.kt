package com.example.cs330_pzv2.presentation.anime_main_page.find_anime.components


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cs330_pzv2.presentation.nav_bar.AnimeNavBarViewModel
import com.example.cs330_pzv2.presentation.nav_bar.NavBar
import com.example.cs330_pzv2.presentation.anime_main_page.find_anime.AnimeMainPageVIewModel

import com.example.cs330_pzv2.presentation.anime_watch_list.AnimeDatabaseViewModel
import com.example.cs330_pzv2.presentation.anime_watch_list.components.AnimeDetailMainItemList
import com.example.cs330_pzv2.presentation.nav_bar.search.AnimeSearchDialog
import com.example.cs330_pzv2.presentation.nav_bar.search.AnimeSearchItemList

@SuppressLint("StateFlowValueCalledInComposition")

@Composable
fun AnimeMainPageScreen (
    navController: NavController,
    viewModel: AnimeMainPageVIewModel = hiltViewModel(),
    navViewModel: AnimeNavBarViewModel = hiltViewModel(),
    databaseViewModel: AnimeDatabaseViewModel = hiltViewModel()

) {

    Column(modifier = Modifier.fillMaxSize()
    ) {
        NavBar(navController = navController)


        if (navViewModel.state.value.dialog){
            AnimeSearchDialog()
        }

        else if (navViewModel.state.value.localDatabase){
            AnimeDetailMainItemList(animeList = databaseViewModel.state.value.animeDetail, title = "Watch List", navController = navController)
        }
        else if (navViewModel.state.value.isSearching){
            AnimeSearchItemList(viewModel.state.value.search_anime,"Searched",navController = navController)
        }
        else if (navViewModel.state.value.isMainPage && !navViewModel.state.value.isSearching) {
            PopularAnime(navController = navController)
        }


    }

}
