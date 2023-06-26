package com.example.cs330_pzv2.domain.use_case.database_use_cases.add_anime

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import com.example.cs330_pzv2.domain.model.AnimeDetail
import com.example.cs330_pzv2.domain.use_case.database_use_cases.FakeAnimeDatabaseRepository
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddAnimeToDatabaseUseCaseTest {
    private lateinit var fakeRepository: FakeAnimeDatabaseRepository
    private lateinit var useCase: AddAnimeToDatabaseUseCase

    @Before
    fun setup() {
        fakeRepository = FakeAnimeDatabaseRepository()
        useCase = AddAnimeToDatabaseUseCase(fakeRepository)
    }

    @Test
    fun testInvoke_shouldInsertNewAnimeToDatabase() = runBlocking {
        val animeDetail = AnimeDetail(
            year = 2023,
            episodes = 12,
            picture = "https://example.com/anime.jpg",
            status = "Airing",
            tags = listOf("Action", "Fantasy"),
            title = "My Anime Title",
            id = 1,
            watched = false
        )

        useCase.invoke(animeDetail)

        val insertedAnime = fakeRepository.getAnimeFromDatabaseById(1).first()
        assertEquals(animeDetail.year, insertedAnime.year)
        assertEquals(animeDetail.episodes, insertedAnime.episodes)
        assertEquals(animeDetail.picture, insertedAnime.picture)
        assertEquals(animeDetail.status, insertedAnime.status)
        assertEquals(animeDetail.tags, insertedAnime.tags)
        assertEquals(animeDetail.title, insertedAnime.title)
        assertEquals(animeDetail.id, insertedAnime.id)
        assertEquals(animeDetail.watched, insertedAnime.watched)
    }
}
