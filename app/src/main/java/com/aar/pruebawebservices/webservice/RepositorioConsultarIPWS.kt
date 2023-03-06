package com.aar.pruebawebservices.webservice

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create




class RepositorioConsultarIPWS
{
    //Ej de URL = "https://ipgeolocation.abstractapi.com/v1/?api_key=cb71b89ab513414b920bff9be723c36c&ip_address=166.171.248.255"
    private val URL_BASE = "https://ipgeolocation.abstractapi.com/"
    private val retrofitService:ConsultarIPService

    //Variable del tipo LiveData que contendra los datos sobre la IP
    val datosIPWSLive = MutableLiveData<DatosIPWS>()


    init
    {
        //Se instancia el Objeto retrofitService para poder hacer la consulta al WebService
        retrofitService = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            //.addConverterFactory(ScalarsConverterFactory.create())//Para pruebas, se obtiene en formato String toda la respuesta del WS
            .baseUrl(URL_BASE)
            .build()
            .create()
    }



    suspend fun conexionWS(direccionIP:String)
    {
        //************** Para pruebas, se devuelven los datos del WS en formato String **************
        //val resultado = retrofitService.getDatosIP("cb71b89ab513414b920bff9be723c36c", direccion_ip)
        //Log.e("Conexion WS", "OK")
        //Log.e("Resultado WS", "${resultado}")

        try
        {
            Log.e("Direccion IP 2", direccionIP)

            //Se consulta en el WS, los datos de la IP pasada como parametro
            val resultadoWS = retrofitService.getDatosIP("cb71b89ab513414b920bff9be723c36c", direccionIP)

            //Para poder guardar los datos en una varible Livedata, solo se puede hacer en el hilo principal
            CoroutineScope(Dispatchers.Main).launch { datosIPWSLive.value = resultadoWS }

        }catch (exception:Exception)
        {
            Log.e("Error WS", "Se ha producido un Error en la Consulta WS")
        }

    }


}