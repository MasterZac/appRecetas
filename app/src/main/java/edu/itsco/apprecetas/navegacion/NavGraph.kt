package edu.itsco.apprecetas.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.itsco.apprecetas.pantallas.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Pantallas.Home.url
    ) {
        composable(route = Pantallas.Home.url){
            HomeScreen(navController = navController)
        }
        composable(route = Pantallas.NuevaReceta.url){
        }
    }

}