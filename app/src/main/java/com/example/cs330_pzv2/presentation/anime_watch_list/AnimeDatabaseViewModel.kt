package com.example.cs330_pzv2.presentation.anime_watch_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cs330_pzv2.domain.use_case.database_use_cases.get_all_anime.GetAllAnimeDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class AnimeDatabaseViewModel @Inject constructor(
    private val getAllAnimeDatabaseUseCase: GetAllAnimeDatabaseUseCase
): ViewModel(){


    private val _state = mutableStateOf(AnimeDatabaseState())
    val state : State<AnimeDatabaseState> = _state

   // private var getAllAnimesJob: Job? =null

    init {
        getAllAnimeDatabase()
    }

    private fun getAllAnimeDatabase(){
       // getAllAnimesJob?.cancel()
       // getAllAnimesJob =
            getAllAnimeDatabaseUseCase()
            .onEach {
                animeDetails ->
                _state.value = state.value.copy(
                    animeDetail = animeDetails
                )
            }.launchIn(viewModelScope)
    }


}