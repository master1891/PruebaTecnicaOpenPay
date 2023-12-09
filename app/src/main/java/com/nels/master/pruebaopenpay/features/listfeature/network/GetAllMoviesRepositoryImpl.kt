package com.nels.master.pruebaopenpay.features.listfeature.network

import com.nels.master.pruebaopenpay.features.listfeature.domain.AllMoviesRepository
import com.nels.master.pruebaopenpay.shared.DataOrError
import com.nels.master.pruebaopenpay.shared.toDataOrError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllMoviesRepositoryImpl @Inject constructor(
    private val apiMovie: ApiMovie
):AllMoviesRepository{

    override suspend fun getAllMovies(): AllMoviesRepository.ResultAllMovies {
        return withContext(Dispatchers.IO){
            when(val response = apiMovie.getAllMovies().toDataOrError()){
                is DataOrError.Data -> AllMoviesRepository.ResultAllMovies.Success(response.value)
                is DataOrError.Error -> AllMoviesRepository.ResultAllMovies.Error(response.message)
            }
        }
    }

}