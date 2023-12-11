package com.nels.master.pruebaopenpay.features.modeoffline.domian.usecases

import com.nels.master.pruebaopenpay.features.locationfeature.domain.LocationsRepository
import com.nels.master.pruebaopenpay.features.locationfeature.domain.models.Posicion
import com.nels.master.pruebaopenpay.features.modeoffline.TypeMoviesFilter
import com.nels.master.pruebaopenpay.features.modeoffline.domian.MoviesDbByFilterRepository
import com.nels.master.pruebaopenpay.features.modeoffline.domian.MoviesDbRepository
import com.nels.master.pruebaopenpay.features.modeoffline.storage.entities.MovieEntity
import java.lang.Exception
import javax.inject.Inject

class GetMoviesDbByFilterUsecase @Inject constructor(
    private val moviesDbByFilterRepository: MoviesDbByFilterRepository
) {
    suspend operator fun invoke(typeMoviesFilter: TypeMoviesFilter): Result{
        return try {

            when(val allMovies
            = moviesDbByFilterRepository.getMoviesDbByFilter(typeMoviesFilter)){
                is MoviesDbByFilterRepository.ResultMoviesDbByFilter.Error -> Result.Error(allMovies.message)
                is MoviesDbByFilterRepository.ResultMoviesDbByFilter.Success -> Result.Success(allMovies.locations)
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