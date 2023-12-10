package com.nels.master.pruebaopenpay.ui.components


import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nels.master.pruebaopenpay.R


@Composable
fun BarraInferior(
    navController: NavHostController,
    items: List<BottomNavigationScreens>
) {
    val currentRoute = rutaActual(navController = navController)


    BottomAppBar {

        items.forEach { screen ->
            NavigationBarItem(
                icon = { 
                       Icon(imageVector = screen.icon, contentDescription = screen.route )
                },
                label = { Text(text = stringResource(id = screen.resourceId))},
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                },
            )
        }
    }
}

@Composable
fun rutaActual(navController: NavHostController): String? {
    val entry by navController.currentBackStackEntryAsState()
    return entry?.destination?.route
}


sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object Home : BottomNavigationScreens("home", R.string.home, Icons.Filled.Home)
    object List : BottomNavigationScreens("list", R.string.today, Icons.Filled.List)
    object Location : BottomNavigationScreens("location", R.string.ubicacion, Icons.Default.Place)
    object Upload :
        BottomNavigationScreens("upload", R.string.upoload, Icons.Outlined.KeyboardArrowUp)
}