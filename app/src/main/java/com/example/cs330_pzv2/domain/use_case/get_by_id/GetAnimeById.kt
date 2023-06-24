package com.example.cs330_pzv2.domain.use_case.get_by_id

import com.example.cs330_pzv2.common.Resource
import com.example.cs330_pzv2.data.mappers.toAnime
import com.example.cs330_pzv2.data.mappers.toAnimeDetail
import com.example.cs330_pzv2.domain.model.Anime
import com.example.cs330_pzv2.domain.model.AnimeDetail
import com.example.cs330_pzv2.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAnimeById @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(animeId: Int): Flow<Resource<AnimeDetail>> = flow {

        try {
            emit(Resource.Loading<AnimeDetail>())
            val anime = repository.getAnimeById(animeId).toAnimeDetail()
            emit(Resource.Success<AnimeDetail>(anime))
        }
        catch (e: HttpException){
            emit(
                Resource.Error<AnimeDetail>(
                    e.localizedMessage?:
                    "An unknown error occurred"
                )
            )
        }
        catch (e : IOException){
            emit(
                Resource.Error<AnimeDetail>(
                    e.localizedMessage?:
                    "Couldn't reach the server, test your internet connection"
                )
            )
        }
    }
}
