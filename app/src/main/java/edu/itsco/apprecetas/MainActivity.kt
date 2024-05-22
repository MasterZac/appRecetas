package edu.itsco.apprecetas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import edu.itsco.apprecetas.ui.theme.AppRecetasTheme
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppRecetasTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppRecetas()
                }
            }
        }
    }
}

@Composable
fun AppRecetas() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "lista_recetas") {
        composable("lista_recetas") { PantallaListaRecetas(navController) }
        composable("detalle_receta/{recetaId}") { backStackEntry ->
            val recetaId = backStackEntry.arguments?.getString("recetaId") ?: "0"
            PantallaDetalleReceta(navController, recetaId)
        }
    }
}

data class Receta(val id: String,
val nombre: String,
val ingredientes: List<String>,
val instrucciones: String,
val imagen: Int )

val recetas = listOf(
    Receta("1", "Spaghetti Carbonara", listOf("Pasta", "Huevos", "Panceta", "Queso parmesano", "Pimienta negra"), "Hervir la pasta. Mezclar huevos y queso. Cocinar la panceta. Combinar todo.", R.drawable.carbonara),
    Receta("2", "Pollo al Curry", listOf("Pollo", "Curry en polvo", "Leche de coco", "Cebolla", "Ajo", "Jengibre"), "Cocinar el pollo. Añadir especias. Agregar leche de coco. Hervir a fuego lento.", R.drawable.curry),
    Receta("3", "Tacos de Res", listOf("Res", "Tortillas", "Lechuga", "Queso", "Salsa"), "Cocinar la res. Llenar las tortillas. Añadir los ingredientes.", R.drawable.res),
    Receta("4", "Ensalada César", listOf("Lechuga", "Aderezo César", "Crutones", "Queso parmesano", "Pollo (opcional)"), "Mezclar todos los ingredientes.", R.drawable.cesar),
    Receta("5", "Pancakes", listOf("Harina", "Leche", "Huevos", "Mantequilla", "Polvo de hornear"), "Mezclar los ingredientes. Cocinar en la plancha.", R.drawable.pancakes),
    Receta("6", "Sopa de Tomate", listOf("Tomates", "Cebolla", "Ajo", "Albahaca", "Crema"), "Cocinar los tomates. Mezclar con los otros ingredientes.", R.drawable.sopa),
    Receta("7", "Sándwich de Queso a la Parrilla", listOf("Pan", "Queso", "Mantequilla"), "Untar el pan con mantequilla. Colocar el queso entre las rebanadas. Asar.", R.drawable.quesoparrilla),
    Receta("8", "Pastel de Chocolate", listOf("Harina", "Azúcar", "Cacao en polvo", "Huevos", "Mantequilla", "Polvo de hornear"), "Mezclar los ingredientes. Hornear.", R.drawable.pastel),
    Receta("9", "Lasaña", listOf("Láminas de lasaña", "Carne molida", "Salsa de tomate", "Queso ricotta", "Queso mozzarella"), "Capas de ingredientes. Hornear.", R.drawable.lasa),
    Receta("10", "Ensalada Griega", listOf("Tomates", "Pepino", "Cebolla", "Aceitunas", "Queso feta", "Aceite de oliva"), "Mezclar todos los ingredientes.", R.drawable.griego)
)

@Composable
fun PantallaListaRecetas(navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Recetas Deliciosas",
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        LazyColumn {
            items(recetas) { receta ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = receta.nombre,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    )
                    Button(
                        onClick = {
                            navController.navigate("detalle_receta/${receta.id}")
                        }
                    ) {
                        Text("Información")
                    }
                }
            }
        }
    }
}

@Composable
fun PantallaDetalleReceta(navController: NavHostController, recetaId: String) {
    val receta = recetas.find { it.id == recetaId }
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            receta?.imagen?.let { imagen ->
                // Mostrar la imagen de la receta si está disponible
                Image(
                    painter = painterResource(id = imagen),
                    contentDescription = "Imagen de la receta",
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(16.dp)) // Espacio entre la imagen y la información de la receta
            receta?.let {
                Text(
                    text = it.nombre,
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ingredientes:",
                    style = MaterialTheme.typography.titleMedium
                )
                it.ingredientes.forEach { ingrediente ->
                    Text(
                        text = "- $ingrediente",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Instrucciones:",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = it.instrucciones,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigateUp() }) {
                    Text("Regresar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaListaRecetasPreview() {
    AppRecetasTheme {
        val navController = rememberNavController()
        PantallaListaRecetas(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaDetalleRecetaPreview() {
    AppRecetasTheme {
        val navController = rememberNavController()
        PantallaDetalleReceta(navController, "1")
    }
}
