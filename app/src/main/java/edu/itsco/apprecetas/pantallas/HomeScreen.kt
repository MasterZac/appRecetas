package edu.itsco.apprecetas.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.itsco.apprecetas.navegacion.Pantallas
import edu.itsco.apprecetas.ui.theme.AppRecetasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Recetas")
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    navController.navigate(
                        route = Pantallas.NuevaReceta.url
                    )
                }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                Text(text = "ADD")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ){
        Column (modifier = Modifier.padding(it)) {

        }
    }
}

@Composable
fun RecetaCard(
    modifier: Modifier = Modifier,
){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column (

        ) {

        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    AppRecetasTheme {
        HomeScreen(navController = rememberNavController())
    }
}