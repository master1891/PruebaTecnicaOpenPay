package com.nels.master.pruebaopenpay.features.modeoffline.storage.impl

import com.nels.master.pruebaopenpay.features.modeoffline.TypeMoviesFilter
import com.nels.master.pruebaopenpay.features.modeoffline.domian.MoviesDbByFilterRepository
import com.nels.master.pruebaopenpay.features.modeoffline.domian.MoviesDbRegisterRepository
import com.nels.master.pruebaopenpay.features.modeoffline.domian.MoviesDbRepository
import com.nels.master.pruebaopenpay.features.modeoffline.storage.dao.MovieDao
import com.nels.master.pruebaopenpay.features.modeoffline.storage.entities.MovieEntity
import javax.inject.Inject

class GetRegisterMoviesDbImpl  constructor(
    private val moviesDao: MovieDao
):MoviesDbRegisterRepository {

    override suspend fun registerMoviesDb(movies: List<MovieEntity>): MoviesDbRegisterRepository.ResultRegisterMoviesDb {
        return try {
            val movies = moviesDao.createMovies(movies)
            MoviesDbRegisterRepository.ResultRegisterMoviesDb.Success
        }catch (ex: Exception){
            MoviesDbRegisterRepository.ResultRegisterMoviesDb.Error (ex.message?:"")
        }
    }

}