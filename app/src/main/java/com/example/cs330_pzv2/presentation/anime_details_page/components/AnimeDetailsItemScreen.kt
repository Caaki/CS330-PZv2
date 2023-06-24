package com.example.cs330_pzv2.presentation.anime_details_page.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cs330_pzv2.presentation.Screen
import com.example.cs330_pzv2.presentation.anime_details_page.AnimeDetailsViewModel
import kotlinx.coroutines.launch


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailsItemScreen(
    modifier: Modifier = Modifier.padding(top = 20.dp),
    navController:NavController,
    viewModel: AnimeDetailsViewModel = hiltViewModel()
) {


    var expanded by remember { mutableStateOf(false) }
    val state = viewModel.state.value
    val coroutineScope = rememberCoroutineScope()


    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                modifier = Modifier
                    .background(Color.Transparent)
                    .scale(1.5f),
                onClick = {
                    navController.popBackStack()
                }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Text(
                text = "Anime List",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )

            IconButton(
                modifier = Modifier
                    .scale(1.5f)
                    .align(Alignment.BottomEnd),
                onClick = {
                    coroutineScope.launch {
                        viewModel.addAnimeToDatabase()
                    }
                    navController.navigate(Screen.AnimeMainPage.route)
                }
            ){
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }


        state.anime?.let {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                AsyncImage(
                    model = it.picture,
                    contentDescription = null,
                    modifier = Modifier
                        .size(340.dp)
                        .aspectRatio(3f / 3f)

                )
                Text(
                    text = "${it.title}",
                    style = MaterialTheme
                        .typography.titleLarge,
                    modifier = Modifier.padding(top = 16.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))

                ) {

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp, vertical = 8.dp)
                    ) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = "Episodes",
                            tint = Color.Green
                        )
                        Text(
                            text = "Edisodes: ${it.episodes}",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${it.status}",
                            color = when (it.status) {
                                "FINISHED" -> {
                                    Color.Green
                                }

                                "ONGOING" -> {
                                    Color.Yellow
                                }

                                "UPCOMING" -> {
                                    Color.Red
                                }

                                else -> {
                                    Color.LightGray
                                }
                            },
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
//                Card(
//                    elevation = CardDefaults.cardElevation(
//                        defaultElevation = 5.dp
//                    ),
//                    modifier = Modifier
//                        .fillMaxWidth()
//
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp)
//                            .clip(MaterialTheme.shapes.small)
//                            .border(1.dp, color =Color.Transparent )
//                    ) {
//                    }
//                }
//            }


                Card(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    ),
                    
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(if (expanded) 300.dp else 150.dp)
                        .clickable { expanded = !expanded }
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .clip(MaterialTheme.shapes.small)
                                    .border(1.dp, color = Color.Transparent)
                            ) {
                                val rows = mutableListOf<MutableList<String>>(mutableListOf())
                                var currentRowCharacterCount = 0

                                it.tags.forEach { tag ->
                                    val tagCharacterCount = tag.length

                                    if (currentRowCharacterCount + tagCharacterCount > 20) {
                                        rows.add(mutableListOf(tag))
                                        currentRowCharacterCount = tagCharacterCount
                                    } else {
                                        val lastRowIndex = rows.lastIndex
                                        rows[lastRowIndex].add(tag)
                                        currentRowCharacterCount += tagCharacterCount
                                    }
                                }

                                rows.forEach { rowTags ->
                                    Row(modifier = Modifier.padding(bottom = 8.dp)) {
                                        rowTags.forEachIndexed { index, tag ->
                                            Box(
                                                modifier = Modifier
                                                    .background(
                                                        MaterialTheme.colorScheme.primary.copy(
                                                            alpha = 0.1f
                                                        ), shape = CircleShape
                                                    )
                                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                                                    .padding(end = 8.dp)
                                            ) {
                                                Text(
                                                    text = tag,
                                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                                    style = MaterialTheme.typography.bodyLarge,
                                                    modifier = Modifier.padding(horizontal = 4.dp)
                                                )
                                            }

                                            // Add spacing between tags
                                            if (index < rowTags.size - 1) {
                                                Spacer(modifier = Modifier.width(8.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }
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

                    )
                }

                if (state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)

                    )
                }
                if (state.isLoading) {
                    CircularProgressIndicator()
                }

            }
        }
    }
}
