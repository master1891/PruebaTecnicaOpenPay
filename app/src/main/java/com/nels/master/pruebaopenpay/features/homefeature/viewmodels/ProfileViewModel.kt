package com.nels.master.pruebaopenpay.features.homefeature.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nels.master.pruebaopenpay.features.homefeature.domain.usecases.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: GetProfileUseCase
):ViewModel() {

    var profileState by mutableStateOf(ProfileState())
        private set

    fun getProfileState(profileId: Int){
        viewModelScope.launch {
            when(val res = profileUseCase(profileId = profileId)){
                is GetProfileUseCase.Result.Error -> {
                    profileState = profileState.copy(
                        state = ProfileStatus.Failure,
                        message = res.message
                    )
                }
                is GetProfileUseCase.Result.Success -> {
                    profileState = profileState.copy(
                        state = ProfileStatus.Success,
                        dataProfile =  res.moviesResponse
                    )
                }
            }
        }
    }

}