package com.nels.master.pruebaopenpay.features.homefeature.domain

import com.nels.master.pruebaopenpay.features.homefeature.network.models.response.profile.ProfileResponse
import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.MoviesResponse

interface FavoriteMoviesUserRepository {

    suspend fun getFavoriteMoviesUser(profileId: Int):ResultFavorites

    //manejador de resultados
    sealed class ResultFavorites{
        data class Success(val result: MoviesResponse):ResultFavorites()
        data class Error(val message: String): ResultFavorites()
    }

}