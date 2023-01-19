package com.aar.pruebawebservices.utils

import android.app.Application
import androidx.room.Room
import com.aar.pruebawebservices.database.PruebaWebServicesDataBase

class PruebaWebServices:Application()
{
    private lateinit var database: PruebaWebServicesDataBase

    override fun onCreate()
    {
        super.onCreate()

        //Se instancia la BD Room
        database = (Room.databaseBuilder(this, PruebaWebServicesDataBase::class.java, "PruebaWebServicesDataBase"))
            .fallbackToDestructiveMigration()
            .build()
    }

    //Se obtiene la Base de Datos de Personas
    fun getPersonaDataBase() = database.personaDao()

}