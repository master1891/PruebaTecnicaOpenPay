package com.nels.master.pruebaopenpay.features.modeoffline.domian.usecases


import com.nels.master.pruebaopenpay.features.modeoffline.domian.ProfileRegisterDbRepository
import com.nels.master.pruebaopenpay.features.modeoffline.storage.entities.ProfileEntity
import java.lang.Exception
import javax.inject.Inject

class GetProfileRegisterDbUsecase constructor(
    private val profileRegisterDbRepository: ProfileRegisterDbRepository
) {
    suspend operator fun invoke(profileEntity: ProfileEntity): Result {
        return try {
            when (val profiles = profileRegisterDbRepository.registerProfileDb(profileEntity)) {
                is ProfileRegisterDbRepository.ResultRegisterProfileDb.Error -> Result.Error(
                    profiles.message
                )
                is ProfileRegisterDbRepository.ResultRegisterProfileDb.Success -> Result.Success
            }
        } catch (ex: Exception) {
            Result.Error("${ex.message}")
        }
    }

    sealed class Result {
        data object Success : Result()
        data class Error(val message: String) : Result()
    }
}