package com.nels.master.pruebaopenpay.features.modeoffline.domian
import com.nels.master.pruebaopenpay.features.modeoffline.TypeMoviesFilter
import com.nels.master.pruebaopenpay.features.modeoffline.storage.entities.MovieEntity

interface  MoviesDbByFilterRepository {

    suspend fun getMoviesDbByFilter(typeMoviesFilter: TypeMoviesFilter):ResultMoviesDbByFilter

    //manejador de resultados
    sealed class ResultMoviesDbByFilter{
        data class Success(val locations: List<MovieEntity>):ResultMoviesDbByFilter()
        data class Error(val message: String): ResultMoviesDbByFilter()
    }
}