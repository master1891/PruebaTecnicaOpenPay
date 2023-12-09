package com.nels.master.pruebaopenpay.features.listfeature.domain.usecase


import com.nels.master.pruebaopenpay.features.listfeature.domain.PopularMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.RecomendationsMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.TopRatedMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.MoviesResponse
import com.nels.master.pruebaopenpay.features.listfeature.network.GetRecomendationsRepositoryImpl
import java.lang.Exception
import javax.inject.Inject

class GetRecomendationsUseCase @Inject constructor(
    private val recomendationsRepository: RecomendationsMoviesRepository,
) {


    suspend operator fun invoke(id_movie: Int): Result{
        return try {
            when(val recomendations = recomendationsRepository.getRecomendations(id_movie)){
                is RecomendationsMoviesRepository.ResultRecomendations.Error -> Result.Error(recomendations.message)
                is  RecomendationsMoviesRepository.ResultRecomendations.Success-> Result.Success(recomendations.result)
            }
        }   catch (ex:Exception){
            Result.Error("${ex.message}")
        }
    }

    sealed class Result {
        data class Success(val moviesResponse: MoviesResponse) : Result()
        data class Error(val mesage: String) : Result()
    }

}