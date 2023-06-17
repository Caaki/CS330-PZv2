package com.example.cs330_pzv2.presentation.anime_main_page.components.TestComponents

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cs330_pzv2.presentation.anime_main_page.AnimeMainPageVIewModel
import com.example.cs330_pzv2.presentation.anime_main_page.components.AnimeMainItemList

@Composable
fun TestScreen(
    navController: NavController,
    viewModel: AnimeMainPageVIewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    LazyColumn(modifier = Modifier.fillMaxSize()) {


        if (state.error.isNotBlank()) {
            item {

                Text(
                    text = state.error,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 400.dp)


                )
            }
        } else if (state.isLoading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(top = 400.dp)
                        .padding(start = 180.dp)
                )
            }
        } else {

            item {
                AnimeMainItemList(animeList = state.action_anime, title = "Action")
            }

            item {
                AnimeMainItemList(animeList = state.mystery_anime, title = "Mystery")
            }

            item {
                AnimeMainItemList(animeList = state.isekai_anime, title = "Romance")
            }

            item {
                AnimeMainItemList(animeList = state.yandere_anime, title = "Yandere")
            }

            item {
                AnimeMainItemList(animeList = state.romance_anime, title = "Romance")
            }
        }
    }
}



