package com.nels.master.pruebaopenpay.features.modeoffline.domian
import com.nels.master.pruebaopenpay.features.modeoffline.storage.entities.MovieEntity

interface  MoviesDbRegisterRepository {

    suspend fun registerMoviesDb(movies: List<MovieEntity>):ResultRegisterMoviesDb
    //manejador de resultados
    sealed class ResultRegisterMoviesDb{
        data object Success :ResultRegisterMoviesDb()
        data class Error(val message: String): ResultRegisterMoviesDb()
    }
}