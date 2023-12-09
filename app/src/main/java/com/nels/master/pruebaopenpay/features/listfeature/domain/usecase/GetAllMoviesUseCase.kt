package com.nels.master.pruebaopenpay.features.listfeature.domain.usecase

import com.nels.master.pruebaopenpay.features.listfeature.domain.AllMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.MoviesResponse
import java.lang.Exception
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(
    private val allMoviesRepository: AllMoviesRepository
) {


    suspend operator fun invoke(): Result{
        return try {
            when(val allMoviesRepository = allMoviesRepository.getAllMovies()){
                is AllMoviesRepository.ResultAllMovies.Error -> Result.Error(allMoviesRepository.message)
                is AllMoviesRepository.ResultAllMovies.Success -> Result.Success(allMoviesRepository.result)
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