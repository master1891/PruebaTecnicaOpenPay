package com.nels.master.pruebaopenpay.features.homefeature.domain.usecases

import com.nels.master.pruebaopenpay.features.homefeature.domain.ProfileRepository
import com.nels.master.pruebaopenpay.features.homefeature.network.models.response.profile.ProfileResponse
import com.nels.master.pruebaopenpay.features.listfeature.domain.AllMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.MoviesResponse
import java.lang.Exception
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {


    suspend operator fun invoke(profileId: Int): Result{
        return try {
            when(val profileResult = profileRepository.getProfile(profileId)){
                is ProfileRepository.ResultProfile.Error -> Result.Error(profileResult.message)
                is ProfileRepository.ResultProfile.Success -> Result.Success(profileResult.result)
            }

        }   catch (ex:Exception){
            Result.Error("${ex.message}")
        }
    }

    sealed class Result {
        data class Success(val moviesResponse: ProfileResponse) : Result()
        data class Error(val message: String) : Result()
    }

}