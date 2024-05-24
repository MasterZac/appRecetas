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
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph
import edu.itsco.apprecetas.navegacion.NavGraph
import edu.itsco.apprecetas.pantallas.RecetaViewModel
import edu.itsco.apprecetas.pantallas.RecetaViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val owner = LocalViewModelStoreOwner.current
            owner?.let {
                val navController = rememberNavController()
                val viewModel: RecetaViewModel = viewModel(
                    it, "RecetaViewModel",
                    RecetaViewModelFactory(
                        application = this.application
                    )
                )
                AppRecetasTheme {
                    NavGraph(navController = navController,
                        viewModel = viewModel)
                }
            }
        }
    }
}

