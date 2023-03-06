package com.aar.pruebawebservices.models

import android.util.Log
import androidx.lifecycle.ViewModel
import com.aar.pruebawebservices.webservice.ConsultarIPService
import com.aar.pruebawebservices.webservice.RepositorioConsultarIPWS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create


class FragmentWSConsultarIPModel:ViewModel()
{

    //Repositorio deonde se realzia la Conexion con el WS
    val repositorioConsultarIPWS = RepositorioConsultarIPWS()

    //************************************ Coroutina en Hilo IO ************************************
    private val viewModelJob = Job()
    private val coroutineScopeIO = CoroutineScope(viewModelJob + Dispatchers.IO)
    //********************************** Fin Coroutina en Hilo IO **********************************

    //************************************* Variables LiveData *************************************
    val datosWSLive = repositorioConsultarIPWS.datosIPWSLive//Variable Livedata con los datos obtenidos del WS, que esta definida en el Repositorio
    //*********************************** Fin Variables LiveData ***********************************




    //Se realiza la conexion con WS
    fun conectarWSIP(direccionIP:String)
    {
        coroutineScopeIO.launch { repositorioConsultarIPWS.conexionWS(direccionIP) }
    }

}