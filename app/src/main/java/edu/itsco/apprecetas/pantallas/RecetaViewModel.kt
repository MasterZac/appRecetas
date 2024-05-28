package edu.itsco.apprecetas.pantallas

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.itsco.apprecetas.data.Receta
import edu.itsco.apprecetas.data.RecetaDB
import edu.itsco.apprecetas.data.RecetaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RecetaViewModel (application: Application): ViewModel(){
    private val recetaRepository: RecetaRepository
    val allReceta: Flow<List<Receta>>

    val listState: StateFlow<HomeUiState>

    init {
        val myDB = RecetaDB.companion.getInstance(application.applicationContext)
        recetaRepository = RecetaRepository(myDB.getRecetaDao())
        allReceta = recetaRepository.getRecetas()
        listState = recetaRepository
            .getRecetas()
            .map {
                HomeUiState(it)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_00L),
                initialValue = HomeUiState()
            )
    }

    suspend fun insertarReceta(receta: Receta){
        recetaRepository.insertarReceta(receta)
    }

    fun getReceta(id: Int): Flow<Receta?>{
        return recetaRepository.getReceta(id)
    }

    data class HomeUiState(
        val list: List<Receta> = listOf()
    )
}

class RecetaViewModelFactory(val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecetaViewModel(application) as T
    }
}
