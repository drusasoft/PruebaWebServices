package com.aar.pruebawebservices.models

import android.content.Context
import androidx.lifecycle.ViewModel
import com.aar.pruebawebservices.database.PersonaDB
import com.aar.pruebawebservices.database.PersonaDao
import com.aar.pruebawebservices.webservice.RepositorioPersonasWS
import kotlinx.coroutines.*





class FragmentWSPersonasModel(private val context: Context, private val dataBase:PersonaDao):ViewModel()
{

    //************************************ Coroutina en Hilo IO ************************************
    private val viewModelJob = Job()
    private val coroutineScopeIO = CoroutineScope(viewModelJob + Dispatchers.IO)
    //********************************** Fin Coroutina en Hilo IO **********************************

    //************************************* Variables LiveData *************************************
    val personasBDLive = dataBase.getPersonasLive()//variable Live data que contiene la lista de todas las personas almacenadas en la BD
    //*********************************** Fin Variables LiveData ***********************************

    //Repsitorio donde se realiza la conexion al WS y se almacenan los resultados en la BD local
    private val repositorioPersonasWS = RepositorioPersonasWS(context, dataBase)



    //Se llama al metodo del repositorio que se encgar de conectar con el WS y obtener los datos
    fun conexionWS()
    {
        coroutineScopeIO.launch{ repositorioPersonasWS.conexionWS("2") }
    }


}