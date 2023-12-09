package com.nels.master.pruebaopenpay.ui.components

import ListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.nels.master.pruebaopenpay.features.locationfeature.LoacationScreen
import com.nels.master.pruebaopenpay.features.uploadfeature.UploadScreen
import com.nels.master.pruebaopenpay.features.homefeature.HomeScreen
import com.nels.master.pruebaopenpay.features.listfeature.viewmodels.ListMoviesViewModel
import com.nels.master.pruebaopenpay.features.locationfeature.viewmodels.MainViewMoldel


@Composable
fun NavigationHost(
   navController: NavHostController,
   lisViewMoldel: ListMoviesViewModel,
   mainViewMoldel: MainViewMoldel
) {

   NavHost(
      navController = navController,
      startDestination = BottomNavigationScreens.Home.route){
      composable(BottomNavigationScreens.Home.route){
         HomeScreen()
      }
      composable(BottomNavigationScreens.List.route){
         ListScreen(lisViewMoldel)
      }
      composable(BottomNavigationScreens.Location.route){
         LoacationScreen(mainViewMoldel)
      }
      composable(BottomNavigationScreens.Upload.route){
         UploadScreen()
      }
   }
}