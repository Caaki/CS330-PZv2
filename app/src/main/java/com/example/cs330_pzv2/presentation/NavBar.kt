package com.example.cs330_pzv2.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cs330_pzv2.presentation.anime_main_page.AnimeMainPageVIewModel

//@Composable
//fun NavBar(
//    navController: NavController,
//    viewModel: AnimeMainPageVIewModel = hiltViewModel()
//) {
//    val tabs = listOf("Entertainment", "Trade", "IT", "All")
//    Column(modifier = Modifier.fillMaxWidth()) {
//        TabRow(selectedTabIndex = viewModel.tabIndex) {
//            tabs.forEachIndexed { index, title ->
//                Tab(text = { Text(title) },
//                    selected = viewModel.tabIndex == index,
//                    onClick = {
//                        viewModel.tabIndex = index
//                        viewModel.setWork(index)
//
//                    }
//                )
//            }
//        }
//
//    }
//}
