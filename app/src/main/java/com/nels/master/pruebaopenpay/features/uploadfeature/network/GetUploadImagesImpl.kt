package com.nels.master.pruebaopenpay.features.uploadfeature.network

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.storage.FirebaseStorage
import com.nels.master.pruebaopenpay.features.uploadfeature.domain.UploadImageRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class GetUploadImagesImpl @Inject constructor(
    private val firebaseStorage: FirebaseStorage
) : UploadImageRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun uploadImages(images: List<Uri>): UploadImageRepository.ResultUpload {
        val reference = firebaseStorage.getReference("/image")

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val nameImage = LocalDateTime.now().format(formatter)
        val  child = reference.child(nameImage)

        return suspendCoroutine { continuation ->

            child.putFile(images[0])
                .addOnSuccessListener {
                    continuation.resumeWith(Result.success(UploadImageRepository.ResultUpload.Success))
                }.addOnFailureListener {
                    continuation.resumeWith(Result.success(UploadImageRepository.ResultUpload.Error(it.message?:"No se pudo subir el archivo")))
                }
        }
    }

}