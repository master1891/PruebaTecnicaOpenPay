package com.nels.master.pruebaopenpay.features.modeoffline.domian
import com.nels.master.pruebaopenpay.features.modeoffline.storage.entities.MovieEntity

interface  MoviesDbRepository {

    suspend fun getAllMovies():ResultMoviesDb

    //manejador de resultados
    sealed class ResultMoviesDb{
        data class Success(val locations: List<MovieEntity>):ResultMoviesDb()
        data class Error(val message: String): ResultMoviesDb()
    }
}