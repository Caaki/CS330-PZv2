package com.example.cs330_pzv2.presentation.anime_watch_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cs330_pzv2.domain.use_case.database_use_cases.get_all_anime.GetAllAnimeDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import com.example.cs330_pzv2.domain.use_case.database_use_cases.get_not_watched_anime.GetNotWatchedAnimeUseCase
import com.example.cs330_pzv2.domain.use_case.database_use_cases.get_watched_anime.GetWatchedAnimeUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class AnimeDatabaseViewModel @Inject constructor(
    private val getNotWatchedAnimeUseCase: GetNotWatchedAnimeUseCase,
    private val getWatchedAnimeUseCase: GetWatchedAnimeUseCase
): ViewModel(){


    private val _state = mutableStateOf(AnimeDatabaseState())
    val state : State<AnimeDatabaseState> = _state


    init {
        getAllAnimeDatabase()
        getWatchedAnimeFromDatabase()
    }

    private fun getWatchedAnimeFromDatabase(){
        getWatchedAnimeUseCase().onEach {
            watched ->
            _state.value = state.value.copy(
                animeDetailFinished = watched
            )
        }.launchIn(viewModelScope)
    }

    private fun getAllAnimeDatabase(){
        getNotWatchedAnimeUseCase()
            .onEach {
                animeDetails ->
                _state.value = state.value.copy(
                    animeDetail = animeDetails
                )
            }.launchIn(viewModelScope)
    }
}