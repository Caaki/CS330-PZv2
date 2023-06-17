package com.example.cs330_pzv2.presentation.anime_main_page.components.TestComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.cs330_pzv2.domain.model.Anime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun TestComposeItem(
    anime: Anime,
    onItemClick: (Anime) ->Unit,
    modifier: Modifier = Modifier
){
    Card(modifier = modifier.clickable { onItemClick(anime) }
        .fillMaxSize()
        .width(280.dp)
        ,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ), shape = MaterialTheme.shapes.large
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = anime.picture),
            contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .aspectRatio(3f / 3f)
                .padding(5.dp)

        )
        Column(
            modifier = Modifier.padding(2.dp
            )
        ) {
            Text(
                text = anime.title.take(20).let { if (anime.title.length > 20) "$it..." else it },
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(

                text = anime.tags.toList()
                    .take(3)
                    .filter { it.length < 12 }
                    .joinToString(", "),
                style = MaterialTheme.typography.bodyLarge,
                modifier = modifier.padding(start = 2.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
                maxItemsInEachRow = 2
            ) {

                AssistChip(
                    onClick = {},
                    colors = AssistChipDefaults.assistChipColors(
                        leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    label = {
                        Text(text = anime.episodes.toString() + " Episodes")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Check,
                            contentDescription = null )
                    }
                )
            }

        }
    }
}