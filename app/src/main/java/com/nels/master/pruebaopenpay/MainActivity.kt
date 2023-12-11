package com.nels.master.pruebaopenpay

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.nels.master.pruebaopenpay.ui.theme.PruebaOpenPayTheme


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.nels.master.pruebaopenpay.features.homefeature.viewmodels.ProfileViewModel
import com.nels.master.pruebaopenpay.features.listfeature.network.ApiMovie
import com.nels.master.pruebaopenpay.features.listfeature.viewmodels.ListMoviesViewModel
import com.nels.master.pruebaopenpay.features.locationfeature.viewmodels.MainViewMoldel
import com.nels.master.pruebaopenpay.features.modeoffline.storage.MoviesDatabase
import com.nels.master.pruebaopenpay.features.uploadfeature.viewmodels.UploafFileViewModel
import com.nels.master.pruebaopenpay.shared.hasLocationPermission
import com.nels.master.pruebaopenpay.ui.components.BarraInferior
import com.nels.master.pruebaopenpay.ui.components.BottomNavigationScreens
import com.nels.master.pruebaopenpay.ui.components.NavigationHost
import com.nels.master.pruebaopenpay.ui.components.RationaleAlert
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notify
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val listMoviesViewModel by viewModels<ListMoviesViewModel>()
    val mainViewModel by viewModels<MainViewMoldel>()
    val uploadViewModel by viewModels<UploafFileViewModel>()
    val profileViewModel by viewModels<ProfileViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            PruebaOpenPayTheme {
                Permisions(context = this, mainViewModel = mainViewModel)
                MainScreen(mainViewModel, listMoviesViewModel, uploadViewModel,profileViewModel)
            }
        }
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Permisions(context :Context, mainViewModel: MainViewMoldel){

    val permisionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    )

    LaunchedEffect(!context.hasLocationPermission()) {
        permisionsState.launchMultiplePermissionRequest()
    }
    when{
        permisionsState.allPermissionsGranted ->{
            mainViewModel.requestLocation(MainViewMoldel.PermissionEvent.Granted)
        }
        permisionsState.shouldShowRationale ->{
            RationaleAlert(onDismiss = { /*TODO*/ }) {
                permisionsState.launchMultiplePermissionRequest()
            }
        }

        !permisionsState.allPermissionsGranted && !permisionsState.shouldShowRationale -> {
            LaunchedEffect(Unit) {
                mainViewModel.requestLocation(MainViewMoldel.PermissionEvent.Revoked)
            }
        }

    }
}


@Composable
fun MainScreen(mainViewModel: MainViewMoldel,listMoviesViewModel: ListMoviesViewModel,
               uploafFileViewModel: UploafFileViewModel, profileViewModel: ProfileViewModel) {

    val navController = rememberNavController()
    val scafold = rememberScrollState()
    val scope = rememberCoroutineScope()

    val navigationImtems = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.List,
        BottomNavigationScreens.Location,
        BottomNavigationScreens.Upload
    )

    Scaffold(
        bottomBar = { BarraInferior(navController, navigationImtems) },

        ) {
        Column(
            modifier = Modifier
                .padding(it),
        ) {
            NavigationHost(
                navController = navController,
                mainViewMoldel = mainViewModel,
                lisViewMoldel = listMoviesViewModel,
                uploafFileViewModel = uploafFileViewModel,
                profileViewModel = profileViewModel
            )
        }
    }
}


