package com.nels.master.pruebaopenpay.features.listfeature.domain

import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.MoviesResponse

interface TopRatedMoviesRepository {

    suspend fun getTopRatedMovies():ResultTopRatedMovies

    //manejador de resultados
    sealed class ResultTopRatedMovies{
        data class Success(val result: MoviesResponse):ResultTopRatedMovies()
        data class Error(val message: String): ResultTopRatedMovies()
    }

}