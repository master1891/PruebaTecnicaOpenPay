package com.nels.master.pruebaopenpay.features.listfeature.network

import com.nels.master.pruebaopenpay.features.listfeature.domain.AllMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.RecomendationsMoviesRepository
import com.nels.master.pruebaopenpay.shared.DataOrError
import com.nels.master.pruebaopenpay.shared.toDataOrError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRecomendationsRepositoryImpl @Inject constructor(
    private val apiMovie: ApiMovie
):RecomendationsMoviesRepository{

    override suspend fun getRecomendations(idMovie: Int): RecomendationsMoviesRepository.ResultRecomendations {
        return withContext(Dispatchers.IO){
            when(val response = apiMovie.getRecomendations(idMovie) .toDataOrError()){
                is DataOrError.Data -> RecomendationsMoviesRepository.ResultRecomendations.Success(response.value)
                is DataOrError.Error -> RecomendationsMoviesRepository.ResultRecomendations.Error(response.message)
            }
        }
    }

}