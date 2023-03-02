package com.aar.pruebawebservices.models

import android.util.Log
import androidx.lifecycle.ViewModel
import com.aar.pruebawebservices.webservice.ConsultarIPService
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

    //************************************ Coroutina en Hilo IO ************************************
    private val viewModelJob = Job()
    private val coroutineScopeIO = CoroutineScope(viewModelJob + Dispatchers.IO)
    //********************************** Fin Coroutina en Hilo IO **********************************

    //************************************* Variables LiveData *************************************

    //*********************************** Fin Variables LiveData ***********************************

    private lateinit var retrofitService:ConsultarIPService




    fun pruebaConectarWSIP()
    {
        val URL_BASE = "https://ipgeolocation.abstractapi.com/"

        retrofitService = Retrofit.Builder()
            //.addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())//Para pruebas, se obtiene en formato String toda la respuesta del WS
            .baseUrl(URL_BASE)
            .build()
            .create()

        coroutineScopeIO.launch {
            val resultado = retrofitService.getDatosIP("cb71b89ab513414b920bff9be723c36c", "166.171.248.255")
            Log.e("Conexion WS", "OK")
            Log.e("Resultado WS", "${resultado}")
        }
    }

}