package com.nels.master.pruebaopenpay.features.modeoffline.storage.impl

import com.nels.master.pruebaopenpay.features.modeoffline.TypeMoviesFilter
import com.nels.master.pruebaopenpay.features.modeoffline.domian.MoviesDbByFilterRepository
import com.nels.master.pruebaopenpay.features.modeoffline.domian.MoviesDbRepository
import com.nels.master.pruebaopenpay.features.modeoffline.storage.dao.MovieDao
import javax.inject.Inject

class GetMoviesDbByFilterImpl  constructor(
    private val moviesDao: MovieDao
):MoviesDbByFilterRepository {

    override suspend fun getMoviesDbByFilter(typeMoviesFilter: TypeMoviesFilter):
            MoviesDbByFilterRepository.ResultMoviesDbByFilter {

        return try {
            val movies = moviesDao.getFilterMovies(typeMoviesFilter.ordinal)
            MoviesDbByFilterRepository.ResultMoviesDbByFilter.Success(movies)
        }catch (ex: Exception){
            MoviesDbByFilterRepository.ResultMoviesDbByFilter.Error (ex.message?:"")
        }
    }

}