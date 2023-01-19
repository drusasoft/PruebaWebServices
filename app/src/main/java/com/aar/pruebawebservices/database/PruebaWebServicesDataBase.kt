package com.aar.pruebawebservices.database

import androidx.room.Database
import androidx.room.RoomDatabase

//*****Nota si se modifica el esquema de la BD, es decir se hace un cambio en esta clase hay que incrementar la version de la BD ya que si no se produce la excepcion
@Database(entities = [PersonaDB::class], version = 1, exportSchema = false)
abstract class PruebaWebServicesDataBase:RoomDatabase()
{
    abstract fun personaDao():PersonaDao
}