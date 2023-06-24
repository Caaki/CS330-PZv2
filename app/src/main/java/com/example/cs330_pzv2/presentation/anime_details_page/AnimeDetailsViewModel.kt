package com.example.cs330_pzv2.presentation.anime_details_page

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cs330_pzv2.common.Constants
import com.example.cs330_pzv2.common.Resource
import com.example.cs330_pzv2.data.database.AnimeDetailEvent
import com.example.cs330_pzv2.data.database.dao.AnimeDetailDao
import com.example.cs330_pzv2.domain.model.AnimeDetail
import com.example.cs330_pzv2.domain.use_case.get_by_id.GetAnimeById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AnimeDetailsViewModel @Inject constructor(
    val getAnimeById: GetAnimeById,
    savedStateHandle: SavedStateHandle,
    private val dao:AnimeDetailDao
): ViewModel(){

    private val _state = MutableStateFlow(AnimeDetailsState())
    val state : MutableStateFlow<AnimeDetailsState> = _state


    init {
        savedStateHandle.get<String>(Constants.ANIME_ID)?.let {
            animeId ->
            getAnime(animeId.toInt())
        }
    }

    fun onEvent(event:AnimeDetailEvent){
        when(event){

            is AnimeDetailEvent.DeleteAnimeDetail -> {
                viewModelScope.launch {
                    dao.deleteAnimeDetail(event.animeDetail)
                }
            }
            else  -> {
                viewModelScope.launch {
                    dao.insertAnimeDetail(animeDetail = state.value.anime?: null)
                }
            }

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