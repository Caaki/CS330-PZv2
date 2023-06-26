package com.example.cs330_pzv2.domain.use_case.database_use_cases.get_all_anime
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
class GetAllAnimeDatabaseUseCaseTest {
    private lateinit var fakeAnimeDatabaseRepository: FakeAnimeDatabaseRepository
    private lateinit var getAllAnimeDatabaseUseCase: GetAllAnimeDatabaseUseCase

    @Before
    fun setUp() {
        fakeAnimeDatabaseRepository = FakeAnimeDatabaseRepository()
        getAllAnimeDatabaseUseCase = GetAllAnimeDatabaseUseCase(fakeAnimeDatabaseRepository)

        runBlocking {
            fakeAnimeDatabaseRepository.insertNewAnime(
                AnimeDetail(
                    year = 2023,
                    episodes = 12,
                    picture = "https://example.com/anime1.jpg",
                    status = "Airing",
                    tags = listOf("Action", "Fantasy"),
                    title = "Anime 1",
                    id = 1,
                    watched = false
                )
            )
            fakeAnimeDatabaseRepository.insertNewAnime(
                AnimeDetail(
                    year = 2023,
                    episodes = 24,
                    picture = "https://example.com/anime2.jpg",
                    status = "Airing",
                    tags = listOf("Action", "Fantasy"),
                    title = "Anime 2",
                    id = 2,
                    watched = true
                )
            )
            fakeAnimeDatabaseRepository.insertNewAnime(
                AnimeDetail(
                    year = 2023,
                    episodes = 10,
                    picture = "https://example.com/anime3.jpg",
                    status = "Airing",
                    tags = listOf("Action", "Fantasy"),
                    title = "Anime 3",
                    id = 3,
                    watched = false
                )
            )
        }
    }

    @Test
    fun testGetAllAnimeDetails() = runTest {
        val expectedListSize = 3
        val expectedFirst = AnimeDetail(
            year = 2023,
            episodes = 12,
            picture = "https://example.com/anime1.jpg",
            status = "Airing",
            tags = listOf("Action", "Fantasy"),
            title = "Anime 1",
            id = 1,
            watched = false
        )

        val actualListResult = getAllAnimeDatabaseUseCase().first()

        assertEquals(expectedListSize, actualListResult.size)
        assertEquals(expectedFirst, actualListResult[0])
    }
}
