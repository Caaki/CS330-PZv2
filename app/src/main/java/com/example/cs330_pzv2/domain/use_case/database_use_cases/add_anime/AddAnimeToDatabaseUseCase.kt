package com.example.cs330_pzv2.domain.use_case.database_use_cases.add_anime

import com.example.cs330_pzv2.domain.model.AnimeDetail
import com.example.cs330_pzv2.domain.repository.AnimeDatabaseRepository
import javax.inject.Inject

class AddAnimeToDatabaseUseCase @Inject constructor(
    private val repository: AnimeDatabaseRepository
) {
    suspend operator fun invoke(animeDetail: AnimeDetail){
        repository.insertNewAnime(animeDetail)
    }
}