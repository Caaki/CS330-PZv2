package com.example.cs330_pzv2.presentation.anime_main_page.find_anime.components

import android.text.style.UnderlineSpan
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
import com.example.cs330_pzv2.domain.model.Anime
import com.example.cs330_pzv2.presentation.Screen
import com.example.cs330_pzv2.presentation.anime_main_page.find_anime.components.TestComponents.TestComposeItem

@Composable
fun AnimeMainItemList(
    animeList: List<Anime>,
    title: String,
    navController:NavController
){
    Text(
        text = title +" anime",
        fontSize = 35.sp,
        modifier = Modifier.padding(
            top = 10.dp,
            start =32.dp ),
        fontFamily = FontFamily.Monospace
    )
    LazyRow(
        modifier = Modifier
            .height(460.dp)
            .padding(horizontal = 16.dp)
    ) {
        items(animeList) { anime ->
            AnimeMainItem(
                anime = anime, onItemClick = {
                    navController.navigate(Screen.AnimeDetailsScreen.route+ "/${anime.id}")
                },
                modifier = Modifier.padding(16.dp)
            )
        }
    }

}