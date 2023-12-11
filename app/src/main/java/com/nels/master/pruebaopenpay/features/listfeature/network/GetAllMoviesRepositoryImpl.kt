package com.nels.master.pruebaopenpay.features.listfeature.network

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.nels.master.pruebaopenpay.features.listfeature.domain.AllMoviesRepository
import com.nels.master.pruebaopenpay.features.modeoffline.TypeMoviesFilter
import com.nels.master.pruebaopenpay.features.modeoffline.domian.usecases.GetRegisterMoviesDbUsecase
import com.nels.master.pruebaopenpay.features.modeoffline.storage.entities.MovieEntity
import com.nels.master.pruebaopenpay.shared.DataOrError
import com.nels.master.pruebaopenpay.shared.toDataOrError
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllMoviesRepositoryImpl @Inject constructor(
    private val apiMovie: ApiMovie,
    private val getRegisterMoviesDbUsecase: GetRegisterMoviesDbUsecase,
    @ApplicationContext private val context: Context
):AllMoviesRepository{

    override suspend fun getAllMovies(): AllMoviesRepository.ResultAllMovies {
        return withContext(Dispatchers.IO){
            when(val response = apiMovie.getAllMovies().toDataOrError()){
                is DataOrError.Data -> {
                    /*
                    val listEntities = response.value.results.map {
                        MovieEntity(
                            title = it.title,
                            typeSource = 0,
                            logo = getBitmap(context,it.poster_path)
                        )
                    }

                     */
                    //getRegisterMoviesDbUsecase.invoke(listEntities)
                    AllMoviesRepository.ResultAllMovies.Success(response.value)
                }
                is DataOrError.Error -> AllMoviesRepository.ResultAllMovies.Error(response.message)
            }
        }
    }

    private suspend fun getBitmap(context: Context,urlString: String): Bitmap {
        val loading = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data("https://image.tmdb.org/t/p/original/".plus( urlString))
            .build()
        val result = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }

}