package com.nels.master.pruebaopenpay.features.uploadfeature.network

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.nels.master.pruebaopenpay.features.uploadfeature.domain.UploadImageRepository
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class GetUploadImagesImpl @Inject constructor(
    private val firebaseStorage: FirebaseStorage
) : UploadImageRepository {
    override suspend fun uploadImages(images: List<Uri>): UploadImageRepository.ResultUpload {
        val reference = firebaseStorage.getReference("/images")

        return suspendCoroutine { continuation ->

            reference.putFile(images[0])
                .addOnSuccessListener {
                    continuation.resumeWith(Result.success(UploadImageRepository.ResultUpload.Success))
                }.addOnFailureListener {
                    continuation.resumeWith(Result.success(UploadImageRepository.ResultUpload.Error(it.message?:"No se pudo subir el archivo")))
                }
        }
    }

}