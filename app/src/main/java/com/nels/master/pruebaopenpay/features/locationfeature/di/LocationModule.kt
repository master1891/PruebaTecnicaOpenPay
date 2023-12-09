package com.nels.master.pruebaopenpay.features.locationfeature.di

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nels.master.pruebaopenpay.features.listfeature.domain.RecomendationsMoviesRepository
import com.nels.master.pruebaopenpay.features.listfeature.network.GetRecomendationsRepositoryImpl
import com.nels.master.pruebaopenpay.features.locationfeature.domain.LocationsRepository
import com.nels.master.pruebaopenpay.features.locationfeature.domain.RegisterLocationRepository
import com.nels.master.pruebaopenpay.features.locationfeature.domain.service.IlocationService
import com.nels.master.pruebaopenpay.features.locationfeature.domain.service.LocationService
import com.nels.master.pruebaopenpay.features.locationfeature.network.GetLocationsRepositoryImpl
import com.nels.master.pruebaopenpay.features.locationfeature.network.GetRegisterLocationRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Singleton
    @Provides
    fun provideLocation(@ApplicationContext context: Context): IlocationService = LocationService(
        context,
        LocationServices.getFusedLocationProviderClient(context)
    )

    @Singleton
    @Provides
    fun provideFirestore():FirebaseFirestore{
        val  db = Firebase.firestore
        return db
    }

    @Provides
    fun provides(impl: GetRegisterLocationRepositoryImpl): RegisterLocationRepository {
        return impl
    }
    @Provides
    fun provideLocations(impl: GetLocationsRepositoryImpl): LocationsRepository {
        return impl
    }
}