package com.nels.master.pruebaopenpay.features.listfeature.domain

import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.MoviesResponse

interface PopularMoviesRepository {

    suspend fun getPopularMovies():ResultPopularMovies

    //manejador de resultados
    sealed class ResultPopularMovies{
        data class Success(val result: MoviesResponse):ResultPopularMovies()
        data class Error(val message: String): ResultPopularMovies()
    }

}