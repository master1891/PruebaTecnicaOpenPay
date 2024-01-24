package com.nels.master.pruebaopenpay.features.homefeature.network

import com.nels.master.pruebaopenpay.features.homefeature.domain.FavoriteMoviesUserRepository
import com.nels.master.pruebaopenpay.features.homefeature.domain.ProfileRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.AllMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.network.ApiMovie
import com.nels.master.pruebaopenpay.shared.DataOrError
import com.nels.master.pruebaopenpay.shared.toDataOrError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class GetFavoriteMoviesUserImpl @Inject constructor(
   @Named("qa") private val apiMovie: ApiMovie
):FavoriteMoviesUserRepository {

    override suspend fun getFavoriteMoviesUser(profileId: Int): FavoriteMoviesUserRepository.ResultFavorites {
        return withContext(Dispatchers.IO){
            when(val response = apiMovie.getFavoriteMoviesUser(profileId).toDataOrError()){
                is DataOrError.Data -> FavoriteMoviesUserRepository.ResultFavorites.Success(response.value)
                is DataOrError.Error -> FavoriteMoviesUserRepository.ResultFavorites.Error(response.message)
            }
        }
    }
}

