package com.example.cs330_pzv2.presentation.anime_main_page

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cs330_pzv2.common.Resource
import com.example.cs330_pzv2.domain.use_case.get_by_tag.GetAnimeByTag
import com.example.cs330_pzv2.domain.use_case.get_by_title.GetAnimeByTitle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AnimeMainPageVIewModel @Inject constructor(
    private val getAnimeByTag : GetAnimeByTag,
    private val getAnimeByTitle: GetAnimeByTitle
): ViewModel(){

    private val _state = mutableStateOf(AnimeMainPageState())
    val state: State<AnimeMainPageState> = _state

    private fun loadAction(tag: String){
        getAnimeByTag(tag).onEach {
            result->
            when(result){
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {

                }
            }
        }.launchIn(viewModelScope)
    }

}