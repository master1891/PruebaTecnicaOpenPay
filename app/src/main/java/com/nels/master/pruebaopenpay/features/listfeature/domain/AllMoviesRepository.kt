package com.nels.master.pruebaopenpay.features.listfeature.domain

import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.MoviesResponse

interface AllMoviesRepository {

    suspend fun getAllMovies():ResultAllMovies

    //manejador de resultados
    sealed class ResultAllMovies{
        data class Success(val result: MoviesResponse):ResultAllMovies()
        data class Error(val message: String): ResultAllMovies()
    }

}