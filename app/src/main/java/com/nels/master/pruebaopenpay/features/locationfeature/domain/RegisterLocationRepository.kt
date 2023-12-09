package com.nels.master.pruebaopenpay.features.locationfeature.domain

import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.MoviesResponse
import com.nels.master.pruebaopenpay.features.locationfeature.domain.models.Posicion

interface  RegisterLocationRepository {

    suspend fun registerLocation(posicion: Posicion):ResultRegisterLocation

    //manejador de resultados
    sealed class ResultRegisterLocation{
        object Success:ResultRegisterLocation()
        data class Error(val message: String): ResultRegisterLocation()
    }


}