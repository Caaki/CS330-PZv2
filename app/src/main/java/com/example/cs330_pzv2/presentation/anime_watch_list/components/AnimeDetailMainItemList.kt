package com.example.cs330_pzv2.presentation.anime_watch_list.components

import android.text.style.UnderlineSpan
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.example.cs330_pzv2.domain.model.AnimeDetail
import com.example.cs330_pzv2.presentation.Screen


@Composable
fun AnimeDetailMainItemList(
    animeList: List<AnimeDetail>,
    title: String,
    navController:NavController,
    modifier: Modifier = Modifier
){
    Text(
        text = title +" anime",
        fontSize = 35.sp,
        modifier = Modifier.padding(
            top = 10.dp,
            start =32.dp ),
        fontFamily = FontFamily.Monospace
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 16.dp)

    ) {
        items(animeList) { anime ->
            AnimeDetailMainItem(
                anime = anime, onItemClick = {
                    navController.navigate(Screen.AnimeDatabaseDetailScreen.route+ "/${anime.id}")
                },
                modifier = Modifier.padding(35.dp)
            )
        }
    }

}