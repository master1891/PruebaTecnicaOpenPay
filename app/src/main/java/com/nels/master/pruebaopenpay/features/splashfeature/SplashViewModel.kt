package com.nels.master.pruebaopenpay.features.splashfeature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    var loadingSplash by mutableStateOf(true)
        private set

    var dataLoade by mutableStateOf(true)
        private set

    fun setLoading() {
        viewModelScope.launch {
            delay(6000)
            loadingSplash = false
        }

    }
}