package com.nels.master.pruebaopenpay.features.listfeature.di

import com.google.gson.GsonBuilder
import com.nels.master.pruebaopenpay.features.homefeature.domain.FavoriteMoviesUserRepository
import com.nels.master.pruebaopenpay.features.homefeature.domain.ProfileRepository
import com.nels.master.pruebaopenpay.features.homefeature.domain.RatedMoviesUserRepository
import com.nels.master.pruebaopenpay.features.homefeature.network.GetFavoriteMoviesUserImpl
import com.nels.master.pruebaopenpay.features.homefeature.network.GetProfileImpl
import com.nels.master.pruebaopenpay.features.homefeature.network.GetRatedMoviesUserImpl
import com.nels.master.pruebaopenpay.features.listfeature.domain.AllMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.PopularMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.RecomendationsMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.domain.TopRatedMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.network.ApiMovie
import com.nels.master.pruebaopenpay.features.listfeature.network.GetAllMoviesRepositoryImpl
import com.nels.master.pruebaopenpay.features.listfeature.network.GetPopularMoviesRepositoryImpl
import com.nels.master.pruebaopenpay.features.listfeature.network.GetRecomendationsRepositoryImpl
import com.nels.master.pruebaopenpay.features.listfeature.network.GetToRayedMoviesRepositoryImpl
import com.nels.master.pruebaopenpay.shared.Cons
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.grpc.android.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.io.encoding.Base64

@Module
@InstallIn(SingletonComponent::class)
class MovieModule {
    @Singleton
    @Provides
    fun getApiMovie(
        client: OkHttpClient,
        converterFactory: Converter.Factory
    ): ApiMovie {
        return Retrofit.Builder()
            .baseUrl(Cons.getUrlBase())
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
            .create(ApiMovie::class.java)
    }

    @Singleton
    @Provides
    fun getOkHttpClient(): OkHttpClient {

        val TIMEOUT = 60
        //val builder: OkHttpClient.Builder
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        val headerValue = "Bearer ".plus( Cons.getKeyAccessToken())

        return OkHttpClient.Builder()
            .callTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor {
                val originalRequest = it.request()
                val builder = originalRequest.newBuilder()
                    .header("Authorization", headerValue)
                val newRequest = builder.build()
                it.proceed(newRequest)
            }
            .build()

    }

    @Singleton
    @Provides
    fun getConverterFactory(): Converter.Factory {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return GsonConverterFactory.create(gson)
    }

    //provides de las capas

    @Provides
    fun getAllMoviesRepository(impl: GetAllMoviesRepositoryImpl): AllMoviesRepository {
        return impl
    }

    @Provides
    fun getPopularMoviesRepository(impl: GetPopularMoviesRepositoryImpl): PopularMoviesRepository {
        return impl
    }

    @Provides
    fun getTopRatedMoviesRepository(impl: GetToRayedMoviesRepositoryImpl): TopRatedMoviesRepository {
        return impl
    }

    @Provides
    fun getRecomendations(impl: GetRecomendationsRepositoryImpl): RecomendationsMoviesRepository {
        return impl
    }

    //para el home

    @Provides
    fun getProfile(impl: GetProfileImpl): ProfileRepository {
        return impl
    }
    @Provides
    fun providesFavoriteUser(impl: GetFavoriteMoviesUserImpl): FavoriteMoviesUserRepository {
        return impl
    }

    @Provides
    fun provedeRatedUser(impl: GetRatedMoviesUserImpl): RatedMoviesUserRepository {
        return impl
    }

}