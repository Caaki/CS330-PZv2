package com.example.cs330_pzv2.presentation.watched_anime_details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cs330_pzv2.common.Constants
import com.example.cs330_pzv2.domain.use_case.database_use_cases.add_anime.AddAnimeToDatabaseUseCase
import com.example.cs330_pzv2.domain.use_case.database_use_cases.delete_anime.DeleteAnimeFromDatabaseUseCase
import com.example.cs330_pzv2.domain.use_case.database_use_cases.get_anime_by_id.GetAnimeByIdFromDatabaseUseCase
import com.example.cs330_pzv2.presentation.anime_details_page.AnimeDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AnimeDatabaseDetailViewModel @Inject constructor(
    private val getAnimeByIdFromDatabaseUseCase: GetAnimeByIdFromDatabaseUseCase,
    savedStateHandle: SavedStateHandle,
    private val addAnimeToDatabaseUseCase: AddAnimeToDatabaseUseCase,
    private val deleteAnimeFromDatabaseUseCase: DeleteAnimeFromDatabaseUseCase
): ViewModel(){

    private val _state = MutableStateFlow(AnimeDetailsState())
    val state : MutableStateFlow<AnimeDetailsState> = _state


    init {
        savedStateHandle.get<String>(Constants.ANIME_ID)?.let {
                animeId ->
            getAnime(animeId.toInt())
        }
    }

    suspend fun changeWatchedStatus(){
        viewModelScope.launch {
            state.value.anime?.let {
                val changed = it.copy(watched = !it.watched)
                addAnimeToDatabaseUseCase(changed)
            }
        }
    }

    suspend fun removeFromDatabase(){
        viewModelScope.launch {
            state.value.anime?.let {
                deleteAnimeFromDatabaseUseCase(it.id)
            }
        }
    }


    private fun getAnime(animeId: Int){
        getAnimeByIdFromDatabaseUseCase(animeId)
            .onEach {
                    animeDetails ->
                _state.value = state.value.copy(
                    anime = animeDetails
                )
            }.launchIn(viewModelScope)
    }
}
