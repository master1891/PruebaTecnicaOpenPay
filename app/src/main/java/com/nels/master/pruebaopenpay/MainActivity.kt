package com.nels.master.pruebaopenpay


import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.nels.master.pruebaopenpay.features.homefeature.viewmodels.ProfileViewModel
import com.nels.master.pruebaopenpay.features.listfeature.viewmodels.ListMoviesViewModel
import com.nels.master.pruebaopenpay.features.locationfeature.viewmodels.MainViewMoldel
import com.nels.master.pruebaopenpay.features.splashfeature.SplashViewModel
import com.nels.master.pruebaopenpay.features.uploadfeature.viewmodels.UploafFileViewModel
import com.nels.master.pruebaopenpay.shared.hasLocationPermission
import com.nels.master.pruebaopenpay.ui.components.BarraInferior
import com.nels.master.pruebaopenpay.ui.components.BottomNavigationScreens
import com.nels.master.pruebaopenpay.ui.components.NavigationHost
import com.nels.master.pruebaopenpay.ui.components.RationaleAlert
import com.nels.master.pruebaopenpay.ui.theme.PruebaOpenPayTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val listMoviesViewModel by viewModels<ListMoviesViewModel>()
    val mainViewModel by viewModels<MainViewMoldel>()
    val uploadViewModel by viewModels<UploafFileViewModel>()
    val profileViewModel by viewModels<ProfileViewModel>()
    val splashViewModel by viewModels<SplashViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSplash()

        setContent {

            PruebaOpenPayTheme {
                Permisions(context = this, mainViewModel = mainViewModel)
                MainScreen(mainViewModel, listMoviesViewModel, uploadViewModel,profileViewModel)
            }
        }
    }


    private fun initSplash(){

        installSplashScreen()
        installSplashScreen().setKeepOnScreenCondition{
            splashViewModel.loadingSplash
        }

        splashViewModel.setLoading()
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


