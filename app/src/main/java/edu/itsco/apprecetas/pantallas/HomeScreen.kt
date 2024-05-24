package edu.itsco.apprecetas.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.itsco.apprecetas.data.Receta
import edu.itsco.apprecetas.navegacion.Pantallas
import edu.itsco.apprecetas.ui.theme.AppRecetasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: RecetaViewModel){
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Recetas",
                    )
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
            ListaRecetas(lista = listOf())
        }
    }
}

@Composable
fun RecetaCard(
    modifier: Modifier = Modifier,
    receta: Receta
){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = receta.nombre,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Button(
                onClick = {
                }
            ) {
                Text(text = "Informacion")
            }
        }
    }
}

@Composable
fun ListaRecetas(lista: List<Receta>){
    LazyColumn () {
        items(lista){
            RecetaCard(receta = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaRecetaPreview(){
    val recetas: List<Receta> = listOf(
        Receta(
            1,
            "receta1",
            "asfjnff",
            "Lorem lorem"
        ),
        Receta(
            2,
            "receta2",
            "Lorem dhidhf",
            "Lorem Lorem"
        )
    )
    ListaRecetas(lista = recetas)
}

//@Preview(showBackground = true)
//@Composable
//fun HomePreview(){
//    AppRecetasTheme {
//        HomeScreen(navController = rememberNavController())
//    }
//}