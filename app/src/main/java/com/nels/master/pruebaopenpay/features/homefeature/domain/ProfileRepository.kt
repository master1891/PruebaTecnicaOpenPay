package com.nels.master.pruebaopenpay.features.homefeature.domain

import com.nels.master.pruebaopenpay.features.homefeature.network.models.response.profile.ProfileResponse
import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.MoviesResponse

interface ProfileRepository {

    suspend fun getProfile(profileId: Int):ResultProfile

    //manejador de resultados
    sealed class ResultProfile{
        data class Success(val result: ProfileResponse):ResultProfile()
        data class Error(val message: String): ResultProfile()
    }

}