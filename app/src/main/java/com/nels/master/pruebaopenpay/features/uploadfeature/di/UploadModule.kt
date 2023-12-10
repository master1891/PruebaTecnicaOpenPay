package com.nels.master.pruebaopenpay.features.uploadfeature.di

import com.google.firebase.storage.FirebaseStorage
import com.nels.master.pruebaopenpay.features.uploadfeature.domain.UploadImageRepository
import com.nels.master.pruebaopenpay.features.uploadfeature.network.GetUploadImagesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UploadModule {

    @Singleton
    @Provides
    fun provideFirebaseDb() =
        FirebaseStorage.getInstance()

    @Singleton
    @Provides
    fun provideUploadFiles( getUploadImagesImpl: GetUploadImagesImpl):UploadImageRepository{
        return getUploadImagesImpl
    }

}