package com.nels.master.pruebaopenpay.features.modeoffline.viewmodels

import com.nels.master.pruebaopenpay.features.homefeature.viewmodels.ProfileStatus

data class OfflineState(
    val message:String = "",
    val status: OfflineStatus = OfflineStatus.Init
)
sealed interface OfflineStatus {
    data object Success : OfflineStatus
    data object Failure : OfflineStatus
    data object Init : OfflineStatus
}