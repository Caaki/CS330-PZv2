package com.example.cs330_pzv2.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.cs330_pzv2.data.database.AnimeDetailDatabase
import com.example.cs330_pzv2.data.database.dao.AnimeDetailDao
import com.example.cs330_pzv2.domain.model.AnimeDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AnimeDetailDaoTest {
    private lateinit var database: AnimeDetailDatabase
    private lateinit var dao: AnimeDetailDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AnimeDetailDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.dao
    }

    @After
    fun cleanup() {
        database.close()
    }

    @Test
    fun insertAnimeDetail_shouldInsertIntoDatabase() = runBlocking(Dispatchers.Main) {
        val anime = AnimeDetail(
            year = 2023,
            episodes = 12,
            picture = "slika",
            status = "Airing",
            tags = listOf("Action", "Fantasy"),
            title = "My Anime Title"
        )

        dao.insertAnimeDetail(anime)

        val insertedAnime = dao.getAnimeDetail().first().first()
        assertEquals(anime, insertedAnime)
    }

    @Test
    fun deleteAnimeDetail_shouldRemoveFromDatabase() = runBlocking(Dispatchers.Main) {
        val anime = AnimeDetail(
            year = 2023,
            episodes = 12,
            picture = "slika",
            status = "Airing",
            tags = listOf("Action", "Fantasy"),
            title = "My Anime Title"
        )
        dao.insertAnimeDetail(anime)

        dao.deleteAnimeDetail(anime)

        val animeList = dao.getAnimeDetail().first()
        assertEquals(0, animeList.size)
    }



    @Test
    fun getWatchedAnimeDetails_shouldReturnWatchedAnime() = runBlocking(Dispatchers.Main) {
        val anime1 = AnimeDetail(
            year = 2023,
            episodes = 12,
            picture = "https://example.com/anime1.jpg",
            status = "Airing",
            tags = listOf("Action", "Fantasy"),
            title = "Anime 1",
            watched = false
        )
        val anime2 = AnimeDetail(
            year = 2023,
            episodes = 12,
            picture = "https://example.com/anime2.jpg",
            status = "Airing",
            tags = listOf("Action", "Fantasy"),
            title = "Anime 2",
            watched = true
        )
        dao.insertAnimeDetail(anime1)
        dao.insertAnimeDetail(anime2)

        val watchedAnimeList = dao.getWatchedAnimeDetails().first()
        assertEquals(1, watchedAnimeList.size)
        assertEquals("Anime 2", watchedAnimeList[0].title)
    }


}

