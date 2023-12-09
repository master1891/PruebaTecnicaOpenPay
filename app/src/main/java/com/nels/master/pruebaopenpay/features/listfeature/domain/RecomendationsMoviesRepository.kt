package com.nels.master.pruebaopenpay.features.listfeature.domain

import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.MoviesResponse

interface RecomendationsMoviesRepository {

    suspend fun getRecomendations(id_movie: Int):ResultRecomendations

    //manejador de resultados
    sealed class ResultRecomendations{
        data class Success(val result: MoviesResponse):ResultRecomendations()
        data class Error(val message: String): ResultRecomendations()
    }

}