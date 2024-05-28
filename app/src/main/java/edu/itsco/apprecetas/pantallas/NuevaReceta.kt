package edu.itsco.apprecetas.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.itsco.apprecetas.R
import edu.itsco.apprecetas.data.Receta
import edu.itsco.apprecetas.navegacion.Pantallas
import edu.itsco.apprecetas.ui.theme.AppRecetasTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevaRecetaScreen(navController: NavController, viewModel: RecetaViewModel){
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Agrega una receta")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(
                            route = Pantallas.Home.url
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
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
            Formulario(Modifier.padding(it), navController, viewModel)
        }
    }
}

@Composable
fun Formulario(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RecetaViewModel
){
    val coroutineScope = rememberCoroutineScope()
    var nombre by remember { mutableStateOf("") }
    var newingrediente by remember { mutableStateOf("") }
    var instrucciones by remember { mutableStateOf("") }
    var ingredientes by remember { mutableStateOf(mutableListOf<String>()) }

    Column (
        modifier = Modifier
            .padding(top = 64.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it},
            label = { Text(text = "Nombre")},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = newingrediente,
            onValueChange = {newingrediente = it},
            label = { Text(text = "Agregar ingrediente")},
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                if (newingrediente.isNotBlank()){
                    ingredientes = (ingredientes + newingrediente).toMutableList()
                    newingrediente = ""
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Añadir Ingrediente")
        }

        Card (
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 200.dp)
                .padding(8.dp)
        ){
            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ){
                items(ingredientes){ ingrediente ->
                    Text(text = "• $ingrediente", modifier = Modifier.padding(4.dp))
                }
            }

        }

        OutlinedTextField(
            value = instrucciones,
            onValueChange = {instrucciones = it},
            label = { Text(text = "Instrucciones")},
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            singleLine = false,
            maxLines = 5
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
        ){
            Button(
                onClick = {
                    coroutineScope.launch {
                        val receta =
                            Receta(
                                0,
                                nombre,
                                ingredientes,
                                instrucciones
                            )
                        viewModel.insertarReceta(
                            receta = receta
                        )
                        navController.navigate(
                            route = Pantallas.Home.url
                        )
                    }
                }
            ){
                Text(text = "Guardar")
            }
            OutlinedButton(
                onClick = {
                    navController.navigate(
                        route = Pantallas.Home.url
                    )
                }
            ) {
                Text(text = "Cancelar")
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewNuevaReceta(){
//    AppRecetasTheme {
//        NuevaRecetaScreen(navController = rememberNavController())
//    }
//}