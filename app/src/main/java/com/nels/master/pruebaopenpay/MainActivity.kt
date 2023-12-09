package com.nels.master.pruebaopenpay

import com.nels.master.pruebaopenpay.ui.theme.PruebaOpenPayTheme


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.nels.master.pruebaopenpay.features.listfeature.network.ApiMovie
import com.nels.master.pruebaopenpay.features.listfeature.viewmodels.ListMoviesViewModel
import com.nels.master.pruebaopenpay.ui.components.BarraInferior
import com.nels.master.pruebaopenpay.ui.components.BottomNavigationScreens
import com.nels.master.pruebaopenpay.ui.components.NavigationHost
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<ListMoviesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getRecomendations(238)

        setContent {
            PruebaOpenPayTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {

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
        bottomBar = {BarraInferior(navController,navigationImtems)},

        ){
        Column(
            modifier = Modifier
                .padding(it),
        ) {
            NavigationHost(navController = navController)
        }
    }
}


