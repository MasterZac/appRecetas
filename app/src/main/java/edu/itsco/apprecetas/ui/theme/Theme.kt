package edu.itsco.apprecetas.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val PaletaColoresOscuros = darkColorScheme(
    primary = Naranja,
    primaryContainer = Rojo,
    secondary = Amarillo,
    background = FondoOscuro,
    onPrimary = FondoOscuro,
    onSecondary = FondoOscuro,
    onBackground = FondoClaro
)

private val PaletaColoresClaros = lightColorScheme(
    primary = Naranja,
    primaryContainer = Rojo,
    secondary = Amarillo,
    background = FondoClaro,
    onPrimary = FondoClaro,
    onSecondary = FondoOscuro,
    onBackground = FondoOscuro
)

@Composable
fun AppRecetasTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colores = if (darkTheme) {
        PaletaColoresOscuros
    } else {
        PaletaColoresClaros
    }

    MaterialTheme(
        colorScheme = colores,
        typography = Tipografia,
        content = content
    )
}
