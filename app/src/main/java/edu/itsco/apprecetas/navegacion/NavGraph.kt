package edu.itsco.apprecetas.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.itsco.apprecetas.pantallas.HomeScreen
import edu.itsco.apprecetas.pantallas.NuevaRecetaScreen
import edu.itsco.apprecetas.pantallas.RecetaViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: RecetaViewModel
){
    NavHost(
        navController = navController,
        startDestination = Pantallas.Home.url
    ) {
        composable(route = Pantallas.Home.url){
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Pantallas.NuevaReceta.url){
            NuevaRecetaScreen(navController = navController, viewModel = viewModel)
        }
    }

}