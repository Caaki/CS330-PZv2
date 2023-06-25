package com.example.cs330_pzv2.presentation.nav_bar.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cs330_pzv2.presentation.nav_bar.AnimeNavBarViewModel
import com.example.cs330_pzv2.presentation.anime_main_page.find_anime.AnimeMainPageVIewModel
import com.example.cs330_pzv2.util.SearchUtil


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeSearchDialog (
    navViewModel: AnimeNavBarViewModel = hiltViewModel(),
    viewModel:AnimeMainPageVIewModel = hiltViewModel()
){


    var selectedGenres by remember { mutableStateOf(emptyList<String>()) }


    var title by remember { mutableStateOf(TextFieldValue("")) }
    val genres = listOf(
        "action",
        "school",
        "comedy",
        "drama",
        "sci-fi",
        "mecha",
        "yandere",
        "mystery",
        "isekai",
        "fantasy",
        "military",
        "music",
        "war",
        "historical",
        "slice of life"
    )

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(4.dp),
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .scale(1.5f),
                        onClick = { viewModel.closeDialog()
                            navViewModel.closeDialog()
                        }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = "Search", style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            item {
                TextField(
                    value = title,
                    onValueChange = { newText ->
                        title = newText
                    },
                    label = { Text("Anime title") },
                    placeholder = { Text("Enter the anime title") },
                )
            }

            item {
                Column {
                    val rows = (genres.size + 2) / 3
                    val columns = 3

                    val chunkedGenres = genres.chunked(columns)

                    chunkedGenres.forEach { rowGenres ->
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            rowGenres.forEach { genre ->
                                Row(
                                    modifier = Modifier.weight(1f),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = selectedGenres.contains(genre),
                                        onCheckedChange = { isChecked ->
                                            if (isChecked) {
                                                selectedGenres = selectedGenres + genre
                                            } else {
                                                selectedGenres = selectedGenres - genre
                                            }
                                        }
                                    )
                                    Text(
                                        text = genre,
                                        modifier = Modifier.padding(start = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
            item {
                Button(onClick = {
                    val selectedGenresString = selectedGenres.joinToString(",")
                    if (!SearchUtil.validateSearch(title.text, selectedGenresString)){
                        navViewModel.closeDialog()
                    }else {
                        viewModel.getSearchCount()
                        viewModel.searchAnime(
                            tags = selectedGenresString, title = title.text
                        )
                        navViewModel.closeDialogSearch()
                    }

                }) {
                    Text(text = "Submit")
                }
            }
        }
    }
}
