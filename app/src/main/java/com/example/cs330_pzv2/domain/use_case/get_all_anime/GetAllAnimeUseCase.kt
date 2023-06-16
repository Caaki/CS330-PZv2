package com.example.cs330_pzv2.domain.use_case.get_all_anime

import com.example.cs330_pzv2.common.Resource
import com.example.cs330_pzv2.data.remote.dto.AnimeDto
import com.example.cs330_pzv2.data.mappers.toAnime
import com.example.cs330_pzv2.domain.model.Anime
import com.example.cs330_pzv2.domain.repository.AnimeRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import retrofit2.HttpException
import java.io.IOException

class GetAllAnimeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {

    operator fun invoke() : Flow<Resource<List<Anime>>> = flow {

        try {
            emit(Resource.Loading<List<Anime>>())
            val animes = repository.getAllAnime().map {
                it.toAnime()
            }
            emit(Resource.Success<List<Anime>>(animes))
        }
        catch (e: HttpException){
            emit(
                Resource.Error<List<Anime>>(
                    e.localizedMessage?:
                    "An unknown error occurred"
                )
            )
        }
        catch (e : IOException){
            emit(
                Resource.Error<List<Anime>>(
                    e.localizedMessage?:
                    "Couldn't reach the server, test your internet connection"
                )
            )
        }
    }

}