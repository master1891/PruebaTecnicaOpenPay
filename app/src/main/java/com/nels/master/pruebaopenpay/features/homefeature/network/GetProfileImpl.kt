package com.nels.master.pruebaopenpay.features.homefeature.network

import com.nels.master.pruebaopenpay.features.homefeature.domain.ProfileRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.AllMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.network.ApiMovie
import com.nels.master.pruebaopenpay.shared.DataOrError
import com.nels.master.pruebaopenpay.shared.toDataOrError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetProfileImpl @Inject constructor(
    private val apiMovie: ApiMovie
):ProfileRepository {

    override suspend fun getProfile(profileId: Int): ProfileRepository.ResultProfile {
        return withContext(Dispatchers.IO){
            when(val response = apiMovie.getProfile(profileId).toDataOrError()){
                is DataOrError.Data -> ProfileRepository.ResultProfile.Success(response.value)
                is DataOrError.Error -> ProfileRepository.ResultProfile.Error(response.message)
            }
        }
    }
}