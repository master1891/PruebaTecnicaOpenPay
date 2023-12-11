package com.nels.master.pruebaopenpay.features.modeoffline.domian.usecases

import com.nels.master.pruebaopenpay.features.modeoffline.domian.MoviesDbRegisterRepository
import com.nels.master.pruebaopenpay.features.modeoffline.storage.entities.MovieEntity
import java.lang.Exception
import javax.inject.Inject

class GetRegisterMoviesDbUsecase constructor(
    private val moviesDbRegisterRepository: MoviesDbRegisterRepository
) {
    suspend operator fun invoke(movies:List<MovieEntity>): Result{
        return try {

            when(val resultRegister =
                moviesDbRegisterRepository.registerMoviesDb(movies)){
                is MoviesDbRegisterRepository.ResultRegisterMoviesDb.Error -> Result.Error(resultRegister.message)
                is MoviesDbRegisterRepository.ResultRegisterMoviesDb.Success -> Result.Success
            }
        }   catch (ex: Exception){
            Result.Error("${ex.message}")
        }
    }
    sealed class Result {
        data object Success : Result()
        data class Error(val message: String) : Result()
    }
}