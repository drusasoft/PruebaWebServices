package com.aar.pruebawebservices.webservice

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create




class RepositorioConsultarIPWS
{
    //Ej de URL = "https://ipgeolocation.abstractapi.com/v1/?api_key=cb71b89ab513414b920bff9be723c36c&ip_address=166.171.248.255"
    private val URL_BASE = "https://ipgeolocation.abstractapi.com/"

    private val retrofitService:ConsultarIPService

    init
    {
        //Se instancia el Objeto retrofitService para poder hacer la consulta al WebService
        retrofitService = Retrofit.Builder()
            //.addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())//Para pruebas, se obtiene en formato String toda la respuesta del WS
            .baseUrl(URL_BASE)
            .build()
            .create()
    }



    suspend fun conexionWS(direccion_ip:String)
    {

        val resultado = retrofitService.getDatosIP("cb71b89ab513414b920bff9be723c36c", direccion_ip)

        Log.e("Conexion WS", "OK")
        Log.e("Resultado WS", "${resultado}")

    }


}