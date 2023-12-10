package com.nels.master.pruebaopenpay.features.homefeature

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nels.master.pruebaopenpay.R
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
            HeaderProfile(profileResponse = profileUser.dataProfile, modifier =Modifier )
        if (favoritesUserState.state == ProfileStatus.Success)
            Carrousel(favoritesUserState.moviesResponse!!.results,"Tus peliculas favoritas")
        if (ratedUserState.state == ProfileStatus.Success)
            Carrousel(ratedUserState.moviesResponse!!.results,"Las peliculas que calificaste")
    }
}

@Composable
fun HeaderProfile(profileResponse: ProfileResponse?,modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxWidth().padding(top = 40.dp),
       verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Image(
            imageVector = Icons.Default.Person,
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )
    }
    profileResponse?.let {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Hola ${profileResponse.username}", textAlign = TextAlign.Center)
    }
}