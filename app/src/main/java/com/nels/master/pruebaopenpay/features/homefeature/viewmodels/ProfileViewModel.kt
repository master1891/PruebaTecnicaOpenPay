package com.nels.master.pruebaopenpay.features.homefeature.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nels.master.pruebaopenpay.features.homefeature.domain.usecases.GetFavoriteMoviesUserUseCase
import com.nels.master.pruebaopenpay.features.homefeature.domain.usecases.GetProfileUseCase
import com.nels.master.pruebaopenpay.features.homefeature.domain.usecases.GetRatedMoviesUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: GetProfileUseCase,
    private val favoriteMoviesUserUseCase: GetFavoriteMoviesUserUseCase,
    private val ratedMoviesUserUseCase: GetRatedMoviesUserUseCase
):ViewModel() {

    var profileState by mutableStateOf(ProfileState())
        private set
    var favoritesUserState by mutableStateOf(ProfileState())
        private set
    var ratedMoviesUserState by mutableStateOf(ProfileState())
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
    
    fun getFavoriteMoviesUser(profileId: Int){
        viewModelScope.launch {
            when(val res = favoriteMoviesUserUseCase(profileId = profileId)){
                is GetFavoriteMoviesUserUseCase.Result.Error -> {
                    favoritesUserState = favoritesUserState.copy(
                        state = ProfileStatus.Failure,
                        message =  res.message
                    )
                }
                is GetFavoriteMoviesUserUseCase.Result.Success -> {
                    favoritesUserState = favoritesUserState.copy(
                        state = ProfileStatus.Success,
                        moviesResponse =  res.moviesResponse
                    )
                }
            }
        }
    }

    fun getRatedMoviesUser(profileId: Int){
        viewModelScope.launch {
            when(val res = ratedMoviesUserUseCase(profileId = profileId)){
                is GetRatedMoviesUserUseCase.Result.Error -> {
                    ratedMoviesUserState = ratedMoviesUserState.copy(
                        state = ProfileStatus.Failure,
                        message =  res.message
                    )
                }
                is GetRatedMoviesUserUseCase.Result.Success -> {
                    ratedMoviesUserState = ratedMoviesUserState.copy(
                        state = ProfileStatus.Success,
                        moviesResponse =  res.moviesResponse
                    )
                }
            }
        }
    }

}