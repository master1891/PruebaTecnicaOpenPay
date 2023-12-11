package com.nels.master.pruebaopenpay.features.modeoffline.domian
import com.nels.master.pruebaopenpay.features.modeoffline.storage.entities.ProfileEntity

interface  ProfileRegisterDbRepository {

    suspend fun registerProfileDb(profileEntity: ProfileEntity):ResultRegisterProfileDb

    //manejador de resultados
    sealed class ResultRegisterProfileDb{
        data object Success :ResultRegisterProfileDb()
        data class Error(val message: String): ResultRegisterProfileDb()
    }
}