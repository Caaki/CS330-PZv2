package com.example.cs330_pzv2.presentation.anime_main_page.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cs330_pzv2.presentation.anime_main_page.AnimeMainPageState
import com.example.cs330_pzv2.presentation.anime_main_page.AnimeMainPageVIewModel

@Composable
fun TestScreen(
    navController: NavController,
    viewModel: AnimeMainPageVIewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Column(modifier = Modifier.fillMaxSize()) {
        LazyRow(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(horizontal = 16.dp)
        ) {
            items(state.isekai_anime) { anime ->
                TestComposeItem(
                    anime = anime, onItemClick = {
                        //navController.navigate(Screen.PersonDetailScreen.route+ "/${person.id}")
                    },
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(horizontal = 16.dp)
        ) {
            items(state.isekai_anime) { anime ->
                TestComposeItem(
                    anime = anime, onItemClick = {
                        //navController.navigate(Screen.PersonDetailScreen.route+ "/${person.id}")
                    },
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            )
        }
    }
}
