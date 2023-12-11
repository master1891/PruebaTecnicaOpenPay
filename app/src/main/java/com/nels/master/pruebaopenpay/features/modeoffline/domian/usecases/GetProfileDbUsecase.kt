package com.nels.master.pruebaopenpay.features.modeoffline.domian.usecases

import com.nels.master.pruebaopenpay.features.modeoffline.domian.ProfileDbRepository
import com.nels.master.pruebaopenpay.features.modeoffline.storage.entities.ProfileEntity
import java.lang.Exception
import javax.inject.Inject

class GetProfileDbUsecase constructor(
    private val profileDbRepository: ProfileDbRepository
) {
    suspend operator fun invoke(): Result{
        return try {
            when(val profiles = profileDbRepository.getProfile()){
                is ProfileDbRepository.ResultProfileDb.Error -> Result.Error(profiles.message)
                is ProfileDbRepository.ResultProfileDb.Success -> Result.Success(profiles.profile)
            }
        }   catch (ex: Exception){
            Result.Error("${ex.message}")
        }
    }

    sealed class Result {
        data class Success(val profiles: List<ProfileEntity> ) : Result()
        data class Error(val message: String) : Result()
    }
}