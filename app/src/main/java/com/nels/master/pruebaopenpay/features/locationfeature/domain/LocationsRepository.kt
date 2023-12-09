package com.nels.master.pruebaopenpay.features.locationfeature.domain

import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.MoviesResponse
import com.nels.master.pruebaopenpay.features.locationfeature.domain.models.Posicion

interface  LocationsRepository {

    suspend fun getLocationsBd():ResultLocationsBd

    //manejador de resultados
    sealed class ResultLocationsBd{
        data class Success(val locations: List<Posicion>):ResultLocationsBd()
        data class Error(val message: String): ResultLocationsBd()
    }
}