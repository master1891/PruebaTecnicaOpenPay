package com.nels.master.pruebaopenpay.features.locationfeature.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nels.master.pruebaopenpay.features.locationfeature.domain.LocationsRepository
import com.nels.master.pruebaopenpay.features.locationfeature.domain.models.Posicion
import com.nels.master.pruebaopenpay.features.locationfeature.domain.usecases.GetLocationUsecase
import com.nels.master.pruebaopenpay.features.locationfeature.domain.usecases.GetLocationsBdUseCase
import com.nels.master.pruebaopenpay.features.locationfeature.domain.usecases.GetRegisterLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MainViewMoldel @Inject constructor(
    private val getLocationUsecase: GetLocationUsecase,
    private val getRegisterLocationUseCase: GetRegisterLocationUseCase,
    private val  getLocationsBdUseCase: GetLocationsBdUseCase
) :ViewModel() {

    val TAG = "MainViewModel"

    var statePosition by mutableStateOf(PositionState())
        private set


    @RequiresApi(Build.VERSION_CODES.O)
    fun requestLocation(evento: PermissionEvent){
        when(evento){
            PermissionEvent.Granted -> {
                viewModelScope.launch {
                    getLocationUsecase.invoke().collect{
                        it?.let {
                            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                            val current = LocalDateTime.now().format(formatter)

                            Log.e(TAG,"Lat: ${it.latitude} Lng: ${it.longitude}")
                            val posicion = Posicion(latitud = it.latitude, longitud = it.longitude, fecha = current )
                            getRegisterLocationUseCase(posicion)
                        }
                    }
                }
            }
            PermissionEvent.Revoked -> {
                Log.e(TAG,"Se quitó el permiso")
            }
        }
    }

    fun getPositions(){
        viewModelScope.launch {
            val res = getLocationsBdUseCase()
            when(res){
                is GetLocationsBdUseCase.Result.Error -> {
                    statePosition = statePosition.copy(
                        status = PositionEvent.Failure
                    )
                }
                is GetLocationsBdUseCase.Result.Success -> {
                    statePosition = statePosition.copy(
                        status = PositionEvent.Success,
                        positions = res.posiciones
                    )
                }
            }
        }

    }

    sealed interface PermissionEvent {
        data object Granted : PermissionEvent
        data object Revoked : PermissionEvent
    }

    sealed class PositionEvent {
        data object Success : PositionEvent()
        data object Failure : PositionEvent()
        data object Init : PositionEvent()
    }

}