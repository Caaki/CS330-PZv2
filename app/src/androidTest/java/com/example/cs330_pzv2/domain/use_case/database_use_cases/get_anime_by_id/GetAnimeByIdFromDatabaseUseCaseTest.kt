package com.example.cs330_pzv2.domain.use_case.database_use_cases.get_anime_by_id

import com.example.cs330_pzv2.domain.model.AnimeDetail
import com.example.cs330_pzv2.domain.repository.AnimeDatabaseRepository
import com.example.cs330_pzv2.domain.use_case.database_use_cases.FakeAnimeDatabaseRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetAnimeByIdFromDatabaseUseCaseTest {

    private lateinit var repository: FakeAnimeDatabaseRepository
    private lateinit var useCase: GetAnimeByIdFromDatabaseUseCase

    @Before
    fun setup() {
        repository = FakeAnimeDatabaseRepository()
        useCase = GetAnimeByIdFromDatabaseUseCase(repository)
    }

    @Test
    fun `invokeShouldReturnAnimeDetailFromDatabase`() = runBlocking {
        val animeId = 123
        val expectedAnimeDetail = getExpectedAnimeDetail()
        repository.insertNewAnime(expectedAnimeDetail)

        val result = useCase(animeId)

        result.collect { animeDetail ->
            assertEquals(expectedAnimeDetail, animeDetail)
        }
    }

    private fun getExpectedAnimeDetail(): AnimeDetail {
        return AnimeDetail(
            year = 2023,
            episodes = 12,
            picture = "https://example.com/anime.jpg",
            status = "Airing",
            tags = listOf("Action", "Fantasy"),
            title = "My Anime Title",
            id = 123,
            watched = false
        )
    }
}
