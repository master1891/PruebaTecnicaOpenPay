package com.nels.master.pruebaopenpay.features.listfeature.domain.usecase


import com.nels.master.pruebaopenpay.features.listfeature.domain.PopularMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.TopRatedMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.MoviesResponse
import java.lang.Exception
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val topRatedMoviesRepository: TopRatedMoviesRepository
) {


    suspend operator fun invoke(): Result{
        return try {
            when(val topRated = topRatedMoviesRepository.getTopRatedMovies()){
                is TopRatedMoviesRepository.ResultTopRatedMovies.Error -> Result.Error(topRated.message)
                is  TopRatedMoviesRepository.ResultTopRatedMovies.Success-> Result.Success(topRated.result)
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