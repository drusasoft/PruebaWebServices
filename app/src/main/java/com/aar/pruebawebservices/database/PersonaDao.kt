package com.aar.pruebawebservices.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonaDao
{

    @Insert(onConflict = OnConflictStrategy.IGNORE)//Con eto evito que se repitan datos de persona en la BD
    fun insertarPersona(persona:PersonaDB)

    @Insert(onConflict = OnConflictStrategy.IGNORE)//Con eto evito que se repitan datos de persona en la BD
    fun insertarPersonas(listaPersonas:List<PersonaDB>)

    @Delete
    fun borrarPersona(persona:PersonaDB)

    @Update
    fun modificarPersona(persona:PersonaDB)

    @Query("Select * from Personas")
    fun getPersonas():List<PersonaDB>

    @Query("Select * from Personas")
    fun getPersonasLive():LiveData<List<PersonaDB>>

    @Query("Update Personas set seleccionada = 1 where id = :id")
    fun seleccionarPersona(id:String)

    @Query("Update Personas set seleccionada = 0")
    fun deseleccionarPersonas()

    @Query("Delete from Personas")
    fun borrarDB()

}