package com.example.cs330_pzv2.domain.use_case.database_use_cases.get_all_anime

import com.example.cs330_pzv2.domain.model.AnimeDetail
import com.example.cs330_pzv2.domain.repository.AnimeDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeDetailSortedById @Inject constructor(
    private val repository: AnimeDatabaseRepository
) {
    operator fun invoke(): Flow<List<AnimeDetail>> {
        return repository.getAnimeDetailSortedById()
    }
}