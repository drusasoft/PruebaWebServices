package com.aar.pruebawebservices.models

import android.content.Context
import androidx.lifecycle.ViewModel
import com.aar.pruebawebservices.webservice.RepositorioExchangeWS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch





class FragmentWSExchangeModel(private val context: Context):ViewModel()
{

    private val repositorioExchangeWS = RepositorioExchangeWS(context)

    //************************************ Coroutina en Hilo IO ************************************
    private val viewModelJob = Job()
    private val coroutineScopeIO = CoroutineScope(viewModelJob + Dispatchers.IO)
    //********************************** Fin Coroutina en Hilo IO **********************************

    //************************************* Variables LiveData *************************************
    val resultadoExchangeLive = repositorioExchangeWS.resultadoExchangeLive
    //*********************************** Fin Variables LiveData ***********************************


    fun conexionExchangeWS(divisaOrigen:String, divisaDestino:String, cantidad:String)
    {
        coroutineScopeIO.launch { repositorioExchangeWS.conexionWS(divisaOrigen, divisaDestino, cantidad) }
    }

}