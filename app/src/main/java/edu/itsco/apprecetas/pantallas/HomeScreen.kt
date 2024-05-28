package edu.itsco.apprecetas.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.itsco.apprecetas.R
import edu.itsco.apprecetas.data.Receta
import edu.itsco.apprecetas.navegacion.Pantallas
import edu.itsco.apprecetas.ui.theme.AppRecetasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: RecetaViewModel){
    val homeUiState by viewModel.listState.collectAsState()
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Recetas",
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
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
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.5f)
            )
            Column(modifier = Modifier.padding(it)) {
                ListaRecetas(lista = homeUiState.list, navController = navController)
            }
        }
    }
}

@Composable
fun RecetaCard(
    modifier: Modifier = Modifier,
    receta: Receta,
    navController: NavController
){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
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
                    navController.navigate(Pantallas.DetalleTarea.url + "/${receta.id}")
                }
            ) {
                Text(text = "Ver receta")
            }
        }
    }
}

@Composable
fun ListaRecetas(lista: List<Receta>, navController: NavController){
    LazyColumn () {
        items(lista){
            RecetaCard(receta = it, navController = navController)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ListaRecetaPreview(){
//    val recetas: List<Receta> = listOf(
//        Receta(
//            1,
//            "receta1",
//            listOf("Ingrediente 1", "Ingrediente 2"),
//            "Lorem lorem"
//        ),
//        Receta(
//            2,
//            "receta2",
//            listOf("Ingrediente1", "Ingrediente2"),
//            "Lorem Lorem"
//        )
//    )
//    val navController = rememberNavController()
//    ListaRecetas(lista = recetas, navController = navController)
//}

//@Preview(showBackground = true)
//@Composable
//fun HomePreview(){
//    AppRecetasTheme {
//        HomeScreen(navController = rememberNavController())
//    }
//}