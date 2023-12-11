package com.nels.master.pruebaopenpay.features.modeoffline.domian.usecases

import com.nels.master.pruebaopenpay.features.locationfeature.domain.LocationsRepository
import com.nels.master.pruebaopenpay.features.locationfeature.domain.models.Posicion
import com.nels.master.pruebaopenpay.features.modeoffline.domian.MoviesDbRepository
import com.nels.master.pruebaopenpay.features.modeoffline.storage.entities.MovieEntity
import java.lang.Exception
import javax.inject.Inject

class GetAllMoviesDbUsecase @Inject constructor(
    private val moviesDbRepository: MoviesDbRepository
) {
    suspend operator fun invoke(): Result{
        return try {
            when(val allMovies = moviesDbRepository.getAllMovies()){
                is MoviesDbRepository.ResultMoviesDb.Error -> Result.Error(allMovies.message)
                is MoviesDbRepository.ResultMoviesDb.Success -> Result.Success(allMovies.locations)
            }

        }   catch (ex: Exception){
            Result.Error("${ex.message}")
        }
    }

    sealed class Result {
        data class Success(val posiciones: List<MovieEntity> ) : Result()
        data class Error(val message: String) : Result()
    }
}