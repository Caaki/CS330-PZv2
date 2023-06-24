package com.example.cs330_pzv2.domain.use_case.database_use_cases.get_anime_sorted_by_title

import com.example.cs330_pzv2.domain.model.AnimeDetail
import com.example.cs330_pzv2.domain.repository.AnimeDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeDetailSortedByTitleUseCase @Inject constructor(
    private val repository: AnimeDatabaseRepository
) {

    operator fun invoke(): Flow<List<AnimeDetail>>{
        return repository.getAnimeDetailSortedByTitle()
    }
}