package com.nels.master.pruebaopenpay.features.listfeature.network
import com.nels.master.pruebaopenpay.features.listfeature.domain.modelos.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiMovie {

    ///Los paths deben estar en el c++
    @GET("discover/movie")
    suspend fun getAllMovies(): Response<MoviesResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): Response<MoviesResponse>

    @GET("movie/{id_movie}/recommendations")
    suspend fun getRecomendations(@Path("id_movie") idMovie:Int): Response<MoviesResponse>

}