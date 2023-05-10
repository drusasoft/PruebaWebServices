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
    val cambioActualLive = repositorioConversorMoneda.cambioActualLive
    //*********************************** Fin Variables LiveData ***********************************



    //Se llama al metodo definido en la clase repositorio que se encarga de hacer la conexion con el WS
    fun conectarWS(monedaOrigenSelecc:String, monedaDestinoSelecc:String)
    {

        //Se obtienen las siglas de las monedas seleccionadas en los SPinners
        val siglasMonedaOrigen = monedaOrigenSelecc.split(",").get(0).trim()
        val siglaMonedaDestino = monedaDestinoSelecc.split(",").get(0).trim()

        coroutineScopeIO.launch { repositorioConversorMoneda.conectarWS(siglasMonedaOrigen, siglaMonedaDestino) }

    }


}