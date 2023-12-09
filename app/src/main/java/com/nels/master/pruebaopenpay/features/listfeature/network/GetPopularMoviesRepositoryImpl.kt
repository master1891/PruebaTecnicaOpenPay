package com.nels.master.pruebaopenpay.features.listfeature.network

import com.nels.master.pruebaopenpay.features.listfeature.domain.PopularMoviesRepository
import com.nels.master.pruebaopenpay.shared.DataOrError
import com.nels.master.pruebaopenpay.shared.toDataOrError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPopularMoviesRepositoryImpl @Inject constructor(
    private val apiMovie: ApiMovie
) : PopularMoviesRepository {

    override suspend fun getPopularMovies(): PopularMoviesRepository.ResultPopularMovies {
        return withContext(Dispatchers.IO) {
            when (val response = apiMovie.getPopularMovies().toDataOrError()) {
                is DataOrError.Data -> PopularMoviesRepository.ResultPopularMovies.Success(response.value)
                is DataOrError.Error -> PopularMoviesRepository.ResultPopularMovies.Error(response.message)
            }
        }
    }
}