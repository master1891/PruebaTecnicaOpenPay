package com.nels.master.pruebaopenpay.features.homefeature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nels.master.pruebaopenpay.features.homefeature.network.models.response.profile.ProfileResponse
import com.nels.master.pruebaopenpay.features.homefeature.viewmodels.ProfileStatus
import com.nels.master.pruebaopenpay.features.homefeature.viewmodels.ProfileViewModel
import com.nels.master.pruebaopenpay.features.listfeature.viewmodels.states.MoviesStates
import com.nels.master.pruebaopenpay.shared.UI.Carrousel

@Composable
fun HomeScreen(profileViewModel: ProfileViewModel) {

    profileViewModel.getProfile(20794159)
    profileViewModel.getFavoriteMoviesUser(20794159)
    profileViewModel.getRatedMoviesUser(20794159)

    val profileUser = profileViewModel.profileState
    val favoritesUserState = profileViewModel.favoritesUserState
    val ratedUserState = profileViewModel.ratedMoviesUserState

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxSize()
    ) {

        if (profileUser.state == ProfileStatus.Success )
            HeaderProfile(profileResponse = profileUser.dataProfile)
        if (favoritesUserState.state == ProfileStatus.Success)
            Carrousel(favoritesUserState.moviesResponse!!.results,"Tus peliculas favoritas")
        if (ratedUserState.state == ProfileStatus.Success)
            Carrousel(ratedUserState.moviesResponse!!.results,"Las peliculas que calificaste")
    }
}

@Composable
fun HeaderProfile(profileResponse: ProfileResponse?) {
    profileResponse?.let {
        Text(text = profileResponse.username)
    }
}