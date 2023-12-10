package com.nels.master.pruebaopenpay.features.uploadfeature.domain

import android.net.Uri
import com.nels.master.pruebaopenpay.features.locationfeature.domain.models.Posicion

interface UploadImageRepository {

    suspend fun uploadImages(images:List<Uri>):ResultUpload

    //manejador de resultados
    sealed class ResultUpload{
        data object Success : ResultUpload()
        data class Error(val message: String): ResultUpload()
    }

}