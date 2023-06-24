package com.example.cs330_pzv2.presentation.anime_main_page.find_anime

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cs330_pzv2.common.Resource
import com.example.cs330_pzv2.data.database.AnimeDetailDatabase
import com.example.cs330_pzv2.domain.use_case.get_anime_for_main_page.GetAnimeByTagForMainPage
import com.example.cs330_pzv2.domain.use_case.get_anime_full_serch.GetAnimeCountUseCase
import com.example.cs330_pzv2.domain.use_case.get_anime_full_serch.GetAnimeFullSearch
import com.example.cs330_pzv2.domain.use_case.get_by_tag.GetAnimeByTag
import com.example.cs330_pzv2.domain.use_case.get_by_title.GetAnimeByTitle
import com.example.cs330_pzv2.presentation.anime_main_page.find_anime.AnimeMainPageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AnimeMainPageVIewModel @Inject constructor(
    private val getAnimeByTag : GetAnimeByTag,
    private val getAnimeFullSearch: GetAnimeFullSearch,
    private val getAnimeByTagForMainPage: GetAnimeByTagForMainPage,
    private val getAnimeCountUseCase: GetAnimeCountUseCase,
): ViewModel(){

    var tabIndex by mutableStateOf(2)

    private val _state = mutableStateOf(AnimeMainPageState())
    val state: State<AnimeMainPageState> = _state

    init{
      loadAllAnime()
    }

    private var dataLoadingCounter = 0

    private fun loadAllAnime(){
        dataLoadingCounter = 5
        loadYandere()
        loadAction()
        loadIsekai()
        loadMystery()
        loadRomance()

    }

    fun refresh(){
        loadAllAnime()
    }

    fun closeDialog(){
        _state.value = _state.value.copy(
            searchTags = "",
            searchString = ""
        )
        loadAllAnime()
    }


    fun getSearchCount(){
        getAnimeCountUseCase(
            tags=_state.value.searchTags,
            title = _state.value.searchString,
        ).onEach {
                result->
            when(result){
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        searchCount = result.data?: 0
                    )
                    decrementDataLoadingCounter()
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchAnime(

        tags:String = _state.value.searchTags,
        title: String = _state.value.searchString,
        page: Int = 1){
        tabIndex = 0
        _state.value = _state.value.copy(
            searchTags = tags,
            searchString = title
        )
        getSearchCount()
        getAnimeFullSearch(
            tags=_state.value.searchTags,
            title = _state.value.searchString,
            page=page).onEach {
                result->
            when(result){
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        search_anime = result.data?: emptyList()
                    )
                    decrementDataLoadingCounter()
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadAction(){
        getAnimeByTagForMainPage("action").onEach {
            result->
            when(result){
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        action_anime = result.data?: emptyList()
                    )
                    decrementDataLoadingCounter()
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadMystery(){
        getAnimeByTagForMainPage("mystery").onEach {
                result->
            when(result){
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        mystery_anime = result.data?: emptyList()
                    )
                    decrementDataLoadingCounter()
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadRomance(){
        getAnimeByTagForMainPage("romance").onEach {
                result->
            when(result){
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        romance_anime = result.data?: emptyList()
                    )
                    decrementDataLoadingCounter()
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadYandere(){
        getAnimeByTag("yandere").onEach {
                result->
            when(result){
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        yandere_anime = result.data?: emptyList()
                    )
                    decrementDataLoadingCounter()
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadIsekai(){
        getAnimeByTagForMainPage("isekai").onEach {
                result->
            when(result){
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isekai_anime = result.data?: emptyList()
                    )
                    decrementDataLoadingCounter()
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun decrementDataLoadingCounter() {
        dataLoadingCounter--
        if (dataLoadingCounter <= 0) {
            _state.value = _state.value.copy(
                isLoading = false
            )
        }
    }

}