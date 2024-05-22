package edu.itsco.apprecetas.navegacion

sealed class Pantallas(val url: String) {
    object Home: Pantallas(url = "Home")
    object NuevaReceta: Pantallas(url = "nuevo_receta")
}