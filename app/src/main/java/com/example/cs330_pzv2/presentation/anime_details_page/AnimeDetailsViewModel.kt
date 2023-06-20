package com.example.cs330_pzv2.presentation.anime_details_page

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cs330_pzv2.common.Constants
import com.example.cs330_pzv2.common.Resource
import com.example.cs330_pzv2.domain.use_case.get_by_id.GetAnimeById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AnimeDetailsViewModel @Inject constructor(
    val getAnimeById: GetAnimeById,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = mutableStateOf(AnimeDetailsState())
    val state : State<AnimeDetailsState> = _state


    init {
        savedStateHandle.get<String>(Constants.ANIME_ID)?.let {
            animeId ->
            getAnime(animeId.toInt())
        }
    }


    private fun getAnime(animeId: Int){

        getAnimeById(animeId).onEach {
            result->

            when(result){
                is Resource.Success -> {
                    _state.value =  AnimeDetailsState(
                        anime = result.data?: null
                    )
                }
                is Resource.Loading -> {
                    _state.value =  AnimeDetailsState(
                        isLoading = true
                    )
                }
                is Resource.Error -> {
                    _state.value =  AnimeDetailsState(
                        error = result.message?:"An unknown error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}