package com.nels.master.pruebaopenpay.features.modeoffline.storage.impl

import com.nels.master.pruebaopenpay.features.modeoffline.domian.MoviesDbRepository
import com.nels.master.pruebaopenpay.features.modeoffline.storage.dao.MovieDao
import javax.inject.Inject

class GetyAllMoviesDbImpl  constructor(
    private val moviesDao: MovieDao
):MoviesDbRepository {
    override suspend fun getAllMovies(): MoviesDbRepository.ResultMoviesDb {

        return try {
            val movies = moviesDao.getMovies()
            MoviesDbRepository.ResultMoviesDb.Success(movies)
        }catch (ex: Exception){
            MoviesDbRepository.ResultMoviesDb.Error (ex.message?:"")
        }
    }

}