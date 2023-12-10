package com.nels.master.pruebaopenpay.features.uploadfeature.domain.usecases

import android.net.Uri
import com.nels.master.pruebaopenpay.features.uploadfeature.domain.UploadImageRepository
import javax.inject.Inject

class GetUploadFileUseCase @Inject constructor(
    private val uploadImageRepository: UploadImageRepository
) {
    suspend operator fun invoke(uris:List<Uri>):Result{

        return when(val resultUpload =  uploadImageRepository.uploadImages(uris)){
            is UploadImageRepository.ResultUpload.Error -> {
               Result.Error(resultUpload.message)
            }
            is UploadImageRepository.ResultUpload.Success -> {
                Result.Success
            }
        }
    }

  sealed class Result {
      data object Success : Result()
      data class Error(val message: String): Result()
  }

}