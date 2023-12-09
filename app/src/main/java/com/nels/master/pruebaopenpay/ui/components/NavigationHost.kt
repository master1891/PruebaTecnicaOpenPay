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



@Composable
fun NavigationHost(navController: NavHostController) {
   NavHost(
      navController = navController,
      startDestination = BottomNavigationScreens.Home.route){
      composable(BottomNavigationScreens.Home.route){
         HomeScreen()
      }
      composable(BottomNavigationScreens.List.route){
         ListScreen()
      }
      composable(BottomNavigationScreens.Location.route){
         LoacationScreen()
      }
      composable(BottomNavigationScreens.Upload.route){
         UploadScreen()
      }
   }
}