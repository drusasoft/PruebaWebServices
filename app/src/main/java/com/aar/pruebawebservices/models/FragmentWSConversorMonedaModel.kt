package com.aar.pruebawebservices.models

import android.content.Context
import androidx.lifecycle.ViewModel
import com.aar.pruebawebservices.webservice.RepositorioConversorMoneda
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch





class FragmentWSConversorMonedaModel(private val context: Context):ViewModel()
{

    //Repositorio deonde se reliza la Conexion con el WS
    private val repositorioConversorMoneda = RepositorioConversorMoneda(context)

    //************************************ Coroutina en Hilo IO ************************************
    private val viewModelJob = Job()
    private val coroutineScopeIO = CoroutineScope(viewModelJob + Dispatchers.IO)
    //********************************** Fin Coroutina en Hilo IO **********************************

    //************************************* Variables LiveData *************************************

    //*********************************** Fin Variables LiveData ***********************************




    fun conectarWS(monedaOrigen:String, monedaDestino:String)
    {

        coroutineScopeIO.launch {  }

    }

}