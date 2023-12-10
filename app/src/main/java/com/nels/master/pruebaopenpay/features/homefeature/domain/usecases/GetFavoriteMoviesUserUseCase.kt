package com.nels.master.pruebaopenpay.features.homefeature.domain.usecases

import com.nels.master.pruebaopenpay.features.homefeature.domain.FavoriteMoviesUserRepository
import com.nels.master.pruebaopenpay.features.homefeature.domain.ProfileRepository
import com.nels.master.pruebaopenpay.features.homefeature.network.models.response.profile.ProfileResponse
import com.nels.master.pruebaopenpay.features.listfeature.domain.AllMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.MoviesResponse
import java.lang.Exception
import javax.inject.Inject

class GetFavoriteMoviesUserUseCase @Inject constructor(
    private val favoriteMoviesUserRepository: FavoriteMoviesUserRepository
) {

    suspend operator fun invoke(profileId: Int): Result{
        return try {
            when(val favoritesUser = favoriteMoviesUserRepository.getFavoriteMoviesUser(profileId)){
                is FavoriteMoviesUserRepository.ResultFavorites.Error -> Result.Error(favoritesUser.message)
                is FavoriteMoviesUserRepository.ResultFavorites.Success -> Result.Success(favoritesUser.result)
            }

        }   catch (ex:Exception){
            Result.Error("${ex.message}")
        }
    }

    sealed class Result {
        data class Success(val moviesResponse: MoviesResponse) : Result()
        data class Error(val message: String) : Result()
    }

}