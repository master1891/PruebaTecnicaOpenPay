package com.nels.master.pruebaopenpay.features.homefeature.network

import com.nels.master.pruebaopenpay.features.homefeature.domain.ProfileRepository
import com.nels.master.pruebaopenpay.features.homefeature.domain.RatedMoviesUserRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.AllMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.network.ApiMovie
import com.nels.master.pruebaopenpay.shared.DataOrError
import com.nels.master.pruebaopenpay.shared.toDataOrError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRatedMoviesUserImpl @Inject constructor(
    private val apiMovie: ApiMovie
):RatedMoviesUserRepository {

    override suspend fun getRatedMoviesUser(profileId: Int): RatedMoviesUserRepository.ResultRated {
        return withContext(Dispatchers.IO){
            when(val response = apiMovie.getRatedMoviesUser(profileId).toDataOrError()){
                is DataOrError.Data -> RatedMoviesUserRepository.ResultRated.Success(response.value)
                is DataOrError.Error -> RatedMoviesUserRepository.ResultRated.Error(response.message)
            }
        }
    }
}