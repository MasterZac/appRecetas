package edu.itsco.apprecetas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Receta::class],
    version = 1,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class RecetaDB: RoomDatabase() {
    abstract fun getRecetaDao(): RecetaDao

    object companion{
        @Volatile
        private var Instance: RecetaDB? = null

        fun getInstance(context: Context): RecetaDB{
            return Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context = context,
                    RecetaDB::class.java,
                    "db_receta")
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}