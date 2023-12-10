package com.nels.master.pruebaopenpay.features.homefeature.viewmodels

import com.nels.master.pruebaopenpay.features.homefeature.network.models.response.profile.ProfileResponse

data class ProfileState(
    val state:ProfileStatus = ProfileStatus.Init,
    val dataProfile:ProfileResponse? = null,
    val message:String = ""
)

sealed interface ProfileStatus{
    data object Success: ProfileStatus
    data object Failure: ProfileStatus
    data object Init: ProfileStatus
}
