package com.example.cs330_pzv2.presentation.anime_main_page.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
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
                .padding(10.dp)

        )
        Column(
            modifier = Modifier.padding(16.dp
            )
        ) {
            Text(
                text = anime.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = anime.tags[1],
                style = MaterialTheme.typography.bodyMedium,

                )

            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                maxItemsInEachRow = 2
            ) {
                AssistChip(
                    onClick = {},
                    colors = AssistChipDefaults.assistChipColors(
                        leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = null )
                    },
                    label = {
                        Text(text = anime.id.toString())
                    }

                )
                AssistChip(
                    onClick = {},
                    colors = AssistChipDefaults.assistChipColors(
                        leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Share,
                            contentDescription = null )
                    },
                    label = {
                        Text(text = anime.episodes.toString())
                    }
                )
            }

        }
    }
}