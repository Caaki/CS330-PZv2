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

    init{

        loadIsekai()

    }

//    private fun loadAllMainPageAnime(){
//        loadShonen()
//        loadAction()
//        loadIsekai()
//        loadMystery()
//        loadRomance()
//    }

    private fun loadAction(){
        getAnimeByTag("action").onEach {
            result->
            when(result){
                is Resource.Success -> {
                    _state.value = AnimeMainPageState(
                        action_anime = result.data?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = AnimeMainPageState(
                        error = result.message?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = AnimeMainPageState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadMystery(){
        getAnimeByTag("mystery").onEach {
                result->
            when(result){
                is Resource.Success -> {
                    _state.value = AnimeMainPageState(
                        mystery_anime = result.data?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = AnimeMainPageState(
                        error = result.message?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = AnimeMainPageState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadRomance(){
        getAnimeByTag("romance").onEach {
                result->
            when(result){
                is Resource.Success -> {
                    _state.value = AnimeMainPageState(
                        romance_anime = result.data?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = AnimeMainPageState(
                        error = result.message?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = AnimeMainPageState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadShonen(){
        getAnimeByTag("shonen").onEach {
                result->
            when(result){
                is Resource.Success -> {
                    _state.value = AnimeMainPageState(
                        shonen_anime = result.data?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = AnimeMainPageState(
                        error = result.message?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = AnimeMainPageState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadIsekai(){
        getAnimeByTag("isekai").onEach {
                result->
            when(result){
                is Resource.Success -> {
                    _state.value = AnimeMainPageState(
                        isekai_anime = result.data?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = AnimeMainPageState(
                        error = result.message?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = AnimeMainPageState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}