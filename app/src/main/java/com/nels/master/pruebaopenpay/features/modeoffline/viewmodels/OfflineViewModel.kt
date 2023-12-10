package com.nels.master.pruebaopenpay.features.modeoffline.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class OfflineViewModel : ViewModel() {
    var offlineState by mutableStateOf(OfflineState())
        private set

}