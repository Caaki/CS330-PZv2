import com.example.cs330_pzv2.domain.model.AnimeDetail
import com.example.cs330_pzv2.domain.repository.AnimeDatabaseRepository
import com.example.cs330_pzv2.domain.use_case.database_use_cases.FakeAnimeDatabaseRepository
import com.example.cs330_pzv2.domain.use_case.database_use_cases.get_watched_anime.GetWatchedAnimeUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetWatchedAnimeUseCaseTest {

    private lateinit var repository: AnimeDatabaseRepository
    private lateinit var useCase: GetWatchedAnimeUseCase

    @Before
    fun setup() {
        repository = FakeAnimeDatabaseRepository()
        useCase = GetWatchedAnimeUseCase(repository)
    }

    @Test
    fun `invokeShouldReturnWatchedAnimeList`() = runBlocking {
        // Arrange
        val watchedAnimeList = listOf(
            AnimeDetail(
                title = "Anime 1",
                episodes = 12,
                picture = "https://example.com/anime1.jpg",
                status = "Airing",
                year = 2023,
                watched = true
            ),
            AnimeDetail(
                title = "Anime 2",
                episodes = 24,
                picture = "https://example.com/anime2.jpg",
                status = "Airing",
                year = 2022,
                watched = true
            ),
            AnimeDetail(
                title = "Anime 3",
                episodes = 12,
                picture = "https://example.com/anime3.jpg",
                status = "Airing",
                year = 2021,
                watched = true
            )
        )
        (repository as FakeAnimeDatabaseRepository).setAnimeList(watchedAnimeList)

        // Act
        val result = useCase()

        // Assert
        result.collect { animeList ->
            assertEquals(watchedAnimeList, animeList)
        }
    }
}
