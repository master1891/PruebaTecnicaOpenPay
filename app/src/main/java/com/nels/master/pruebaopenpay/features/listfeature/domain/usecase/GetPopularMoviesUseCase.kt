package com.nels.master.pruebaopenpay.features.listfeature.domain.usecase


import com.nels.master.pruebaopenpay.features.listfeature.domain.PopularMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.MoviesResponse
import java.lang.Exception
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val popularMoviesRepository: PopularMoviesRepository
) {


    suspend operator fun invoke(): Result{
        return try {
            when(val popular = popularMoviesRepository.getPopularMovies()){
                is PopularMoviesRepository.ResultPopularMovies.Error -> Result.Error(popular.message)
                is  PopularMoviesRepository.ResultPopularMovies.Success-> Result.Success(popular.result)
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