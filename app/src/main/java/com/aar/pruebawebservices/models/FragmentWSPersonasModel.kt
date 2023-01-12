package com.aar.pruebawebservices.models

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.aar.pruebawebservices.R
import com.aar.pruebawebservices.webservice.RepositorioPersonasWS
import kotlinx.coroutines.*





class FragmentWSPersonasModel(private val context: Context):ViewModel()
{

    //************************************ Coroutina en Hilo IO ************************************
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)
    //********************************** Fin Coroutina en Hilo IO **********************************

    //Repsitorio donde se realiza la conexion al WS y se almacenan los resultados en la BD local
    private val repositorioPersonasWS = RepositorioPersonasWS(context)



    //Se llama al metodo del repositorio que se encgar de conectar con el WS y obtener los datos
    fun conexionWS()
    {
        coroutineScope.launch{ repositorioPersonasWS.conexionWS("1") }
    }

}