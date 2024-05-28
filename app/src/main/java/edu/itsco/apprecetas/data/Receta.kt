package edu.itsco.apprecetas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recetas")
data class Receta (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nombre: String,
    val ingredientes: List<String>,
    val instrucciones: String
)