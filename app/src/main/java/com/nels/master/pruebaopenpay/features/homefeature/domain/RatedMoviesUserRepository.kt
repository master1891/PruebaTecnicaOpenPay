package com.nels.master.pruebaopenpay.features.homefeature.domain

import com.nels.master.pruebaopenpay.features.homefeature.network.models.response.profile.ProfileResponse
import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.MoviesResponse

interface RatedMoviesUserRepository {

    suspend fun getRatedMoviesUser(profileId: Int):ResultRated

    //manejador de resultados
    sealed class ResultRated{
        data class Success(val result: MoviesResponse):ResultRated()
        data class Error(val message: String): ResultRated()
    }

}