package edu.itsco.apprecetas.pantallas

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.itsco.apprecetas.data.Receta
import edu.itsco.apprecetas.data.RecetaDB
import edu.itsco.apprecetas.data.RecetaRepository
import kotlinx.coroutines.flow.Flow

class RecetaViewModel (application: Application): ViewModel(){
    private val recetaRepository: RecetaRepository
    val allReceta: Flow<List<Receta>>

    init {
        val myDB = RecetaDB.companion.getInstance(application.applicationContext)
        recetaRepository = RecetaRepository(myDB.getRecetaDao())
        allReceta = recetaRepository.getRecetas()
    }

    suspend fun insertarReceta(receta: Receta){
        recetaRepository.insertarReceta(receta)
    }
}

class RecetaViewModelFactory(val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecetaViewModel(application) as T
    }
}
