package com.example.cs330_pzv2.presentation.anime_main_page.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cs330_pzv2.domain.model.Anime
import com.example.cs330_pzv2.presentation.anime_main_page.components.TestComponents.TestComposeItem

@Composable
fun AnimeMainItemList(
    animeList: List<Anime>,
    title: String
){
    Text(
        text = title +" anime",
        fontSize = 50.sp,
        modifier = Modifier.padding(10.dp)
    )
    LazyRow(
        modifier = Modifier
            .height(480.dp)
            .padding(horizontal = 16.dp)
    ) {
        items(animeList) { anime ->
            TestComposeItem(
                anime = anime, onItemClick = {
                    //navController.navigate(Screen.PersonDetailScreen.route+ "/${person.id}")
                },
                modifier = Modifier.padding(16.dp)
            )
        }
    }

}