package com.example.cs330_pzv2.domain.use_case.database_use_cases.get_anime_by_id

import com.example.cs330_pzv2.domain.model.Anime
import com.example.cs330_pzv2.domain.model.AnimeDetail
import com.example.cs330_pzv2.domain.repository.AnimeDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeByIdFromDatabaseUseCase @Inject constructor(
    private val repository: AnimeDatabaseRepository
) {

    operator fun invoke(animeId: Int): Flow<AnimeDetail> {
        return repository.getAnimeFromDatabaseById(animeId)
    }

}