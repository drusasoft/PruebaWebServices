package com.aar.pruebawebservices.models

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.aar.pruebawebservices.webservice.HoraService
import com.aar.pruebawebservices.webservice.RepositorioConsultarHoraWS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create





class FragmentWSConsultarHoraModel(private val context: Context):ViewModel()
{

    //Repositorio deonde se realzia la Conexion con el WS
    private val repositorioConsultarHoraWS = RepositorioConsultarHoraWS(context)

    //************************************ Coroutina en Hilo IO ************************************
    private val viewModelJob = Job()
    private val coroutineScopeIO = CoroutineScope(viewModelJob + Dispatchers.IO)
    //********************************** Fin Coroutina en Hilo IO **********************************

    //************************************* Variables LiveData *************************************
    val datosWSLive =repositorioConsultarHoraWS.datosHoraLive
    //*********************************** Fin Variables LiveData ***********************************




    fun limpiarVariablesLiveData() { datosWSLive.value = null }



    //Se realiza la conexion con WS
    fun conexionWS(localizacion:String)
    {
        coroutineScopeIO.launch { repositorioConsultarHoraWS.conexionWS(localizacion) }
    }

}