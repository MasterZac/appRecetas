package edu.itsco.apprecetas.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.itsco.apprecetas.navegacion.Pantallas
import edu.itsco.apprecetas.ui.theme.AppRecetasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevaRecetaScreen(navController: NavController){
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
                }
            )
        }
    ){
        Formulario(Modifier.padding(it), navController)
    }
}

@Composable
fun Formulario(modifier: Modifier = Modifier, navController: NavController){
    var nombre by remember { mutableStateOf("") }
    var newingrediente by remember { mutableStateOf("") }
    var instrucciones by remember { mutableStateOf("") }
    var ingredientes by remember { mutableStateOf(listOf<String>()) }

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
                    ingredientes = ingredientes + newingrediente
                    newingrediente = ""
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Añadir Ingrediente")
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

@Preview(showBackground = true)
@Composable
fun PreviewNuevaReceta(){
    AppRecetasTheme {
        NuevaRecetaScreen(navController = rememberNavController())
    }
}