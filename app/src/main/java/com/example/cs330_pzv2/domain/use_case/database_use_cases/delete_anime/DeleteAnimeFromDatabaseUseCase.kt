package com.example.cs330_pzv2.domain.use_case.database_use_cases.delete_anime

import com.example.cs330_pzv2.domain.model.AnimeDetail
import com.example.cs330_pzv2.domain.repository.AnimeDatabaseRepository
import javax.inject.Inject

class DeleteAnimeFromDatabaseUseCase @Inject constructor(
    private val repository: AnimeDatabaseRepository
) {
    suspend operator fun invoke(animeId: Int){
        repository.deleteAnimeFromDatabaseById(animeId)
    }
}