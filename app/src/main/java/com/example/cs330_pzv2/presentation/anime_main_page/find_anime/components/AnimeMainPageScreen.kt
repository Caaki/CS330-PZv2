package com.example.cs330_pzv2.presentation.anime_main_page.find_anime.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cs330_pzv2.presentation.nav_bar.AnimeNavBarViewModel
import com.example.cs330_pzv2.presentation.nav_bar.NavBar
import com.example.cs330_pzv2.presentation.anime_main_page.find_anime.AnimeMainPageVIewModel
import com.example.cs330_pzv2.presentation.nav_bar.search.AnimeSearchDialog
import com.example.cs330_pzv2.presentation.nav_bar.search.AnimeSearchItemList

@Composable
fun AnimeMainPageScreen (
    navController: NavController,
    viewModel: AnimeMainPageVIewModel = hiltViewModel(),
    navViewModel: AnimeNavBarViewModel = hiltViewModel()
) {

    Column(modifier = Modifier.fillMaxSize()
    ) {
        NavBar(navController = navController)


        if (navViewModel.state.value.dialog){
            AnimeSearchDialog()
        }
        else if (navViewModel.state.value.isSearching){
            AnimeSearchItemList(viewModel.state.value.search_anime,"Searched")
        }
        else if (navViewModel.state.value.isMainPage && !navViewModel.state.value.isSearching) {
            PopularAnime(navController = navController)
        }


    }

}
