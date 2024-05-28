package edu.itsco.apprecetas.data

import kotlinx.coroutines.flow.Flow


class RecetaRepository (private val daoReceta: RecetaDao){
    fun getRecetas(): Flow<List<Receta>> = daoReceta.getAll()

    suspend fun insertarReceta(receta: Receta) = daoReceta.insert(receta)

    fun getReceta(id: Int): Flow<Receta> = daoReceta.getReceta(id)
}