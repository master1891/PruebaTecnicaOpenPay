package com.nels.master.pruebaopenpay.features.listfeature.di

import com.nels.master.pruebaopenpay.shared.Cons
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleDemo {

    @Singleton
    @Provides
    @Named("segundo")
    fun getOkHttpClient(myClient:String): OkHttpClient {

        val TIMEOUT = 60
        //val builder: OkHttpClient.Builder
        //val loggingInterceptor = HttpLoggingInterceptor()
        //loggingInterceptor.setLevel(if (BuildConfig) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        val headerValue = "Bearer ".plus( Cons.getKeyAccessToken())

        return OkHttpClient.Builder()
            .callTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            //.addInterceptor(loggingInterceptor)
            .addInterceptor {
                val originalRequest = it.request()
                val builder = originalRequest.newBuilder()
                    .header("Authorization", headerValue)
                val newRequest = builder.build()
                it.proceed(newRequest)
            }
            .build()

    }
}