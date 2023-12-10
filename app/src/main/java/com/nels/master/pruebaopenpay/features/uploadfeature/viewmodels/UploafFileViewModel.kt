package com.nels.master.pruebaopenpay.features.uploadfeature.viewmodels

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nels.master.pruebaopenpay.features.uploadfeature.domain.usecases.GetUploadFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UploafFileViewModel @Inject constructor(
    private val getUploadFileUseCase: GetUploadFileUseCase
):ViewModel() {

    var statusUpload by  mutableStateOf(UploadImagesState())
        private set

    fun uploadImages(images:List<Uri>){
        viewModelScope.launch {

            when (val result = getUploadFileUseCase(images)){
                is GetUploadFileUseCase.Result.Error -> {
                    statusUpload = statusUpload.copy(
                        status = StatusUpload.Failed,
                        message = result.message
                    )
                }
                is GetUploadFileUseCase.Result.Success -> {
                    statusUpload = statusUpload.copy(
                        status = StatusUpload.Uploaded,
                        message = "Archivos subidos con éxito"
                    )
                }
            }
        }
    }

    fun clearStatus(){
        statusUpload = statusUpload.copy(
            status = StatusUpload.init
        )
    }

    sealed interface StatusUpload{
        data object Failed : StatusUpload
        data object Uploaded : StatusUpload
        data object Uploading : StatusUpload
        data object init : StatusUpload
    }

}