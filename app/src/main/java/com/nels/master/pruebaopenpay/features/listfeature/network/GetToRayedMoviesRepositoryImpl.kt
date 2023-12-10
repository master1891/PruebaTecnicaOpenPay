package com.nels.master.pruebaopenpay.features.listfeature.network

import com.nels.master.pruebaopenpay.features.listfeature.domain.PopularMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.TopRatedMoviesRepository
import com.nels.master.pruebaopenpay.shared.DataOrError
import com.nels.master.pruebaopenpay.shared.toDataOrError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetToRayedMoviesRepositoryImpl @Inject constructor(
    private val apiMovie: ApiMovie
) : TopRatedMoviesRepository {

    override suspend fun getTopRatedMovies(): TopRatedMoviesRepository.ResultTopRatedMovies {
        return withContext(Dispatchers.IO) {
            when (val response = apiMovie.getTopRatedMovies().toDataOrError()) {
                is DataOrError.Data -> TopRatedMoviesRepository.ResultTopRatedMovies.Success(response.value)
                is DataOrError.Error -> TopRatedMoviesRepository.ResultTopRatedMovies.Error(response.message)
            }
        }
    }
}