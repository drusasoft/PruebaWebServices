package com.aar.pruebawebservices.models

import android.content.Context
import androidx.lifecycle.ViewModel
import com.aar.pruebawebservices.webservice.RepositorioConsultarIPWS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch





class FragmentWSConsultarIPModel(private val context: Context):ViewModel()
{

    //Repositorio deonde se realzia la Conexion con el WS
    val repositorioConsultarIPWS = RepositorioConsultarIPWS(context)

    //************************************ Coroutina en Hilo IO ************************************
    private val viewModelJob = Job()
    private val coroutineScopeIO = CoroutineScope(viewModelJob + Dispatchers.IO)
    //********************************** Fin Coroutina en Hilo IO **********************************

    //************************************* Variables LiveData *************************************
    val datosWSLive = repositorioConsultarIPWS.datosIPLive//Variable Livedata con los datos obtenidos del WS, que esta definida en el Repositorio
    //*********************************** Fin Variables LiveData ***********************************



    override fun onCleared()
    {
        super.onCleared()

        //Se cancela el Job de la Coroutina donde se ejecuta Retrofit
        viewModelJob.cancel()
    }



    fun limpiarVariablesLiveData()
    {
        repositorioConsultarIPWS.datosIPLive.value?.direccion_ip = "--"
    }

    //Se realiza la conexion con WS
    fun conectarWSIP(direccionIP:String)
    {
        coroutineScopeIO.launch { repositorioConsultarIPWS.conexionWS(direccionIP) }
    }

}