package edu.itsco.apprecetas.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecetaDao {

    @Query("SELECT * FROM recetas")
    fun getAll(): Flow<List<Receta>>

    @Query("SELECT * FROM recetas WHERE id = :id")
    fun getReceta(id: Int): Flow<Receta>

    @Insert
    suspend fun insert(receta: Receta)
}