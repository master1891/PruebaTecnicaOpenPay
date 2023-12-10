package com.nels.master.pruebaopenpay.features.uploadfeature.network

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.storage.FirebaseStorage
import com.nels.master.pruebaopenpay.features.uploadfeature.domain.UploadImageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GetUploadImagesImpl @Inject constructor(
    private val firebaseStorage: FirebaseStorage
) : UploadImageRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun uploadImages(images: List<Uri>): UploadImageRepository.ResultUpload {
        val reference = firebaseStorage.getReference("/image")
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")


        return suspendCoroutine { continuation ->
            try {

               runBlocking {
                    for (imageUri in images) {
                        val nameImage = LocalDateTime.now().format(formatter)
                        val child = reference.child(nameImage)
                        child.putFile(imageUri).await()
                    }
                }
                continuation.resumeWith(Result.success(UploadImageRepository.ResultUpload.Success))

            } catch (ex: Exception) {
                continuation.resumeWith(
                    Result.success(
                        UploadImageRepository.ResultUpload.Error(
                            ex.message ?: "No se pudo finalizar le proceso"
                        )
                    )
                )
            }


        }
    }

}