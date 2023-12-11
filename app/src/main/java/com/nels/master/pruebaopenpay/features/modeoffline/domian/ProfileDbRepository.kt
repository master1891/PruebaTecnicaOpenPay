package com.nels.master.pruebaopenpay.features.modeoffline.domian
import com.nels.master.pruebaopenpay.features.modeoffline.storage.entities.ProfileEntity

interface  ProfileDbRepository {

    suspend fun getProfile():ResultProfileDb

    //manejador de resultados
    sealed class ResultProfileDb{
        data class Success(val profile: List<ProfileEntity>):ResultProfileDb()
        data class Error(val message: String): ResultProfileDb()
    }
}