package com.aar.pruebawebservices.webservice

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.aar.pruebawebservices.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create




class RepositorioConsultarHoraWS(private val context:Context)
{
    //Ej de URL = https://timezone.abstractapi.com/v1/current_time/?api_key=f54d2ec0c6cd44979a501f481dbf6027&location=Tokyo,%20Japan
    private val BASE_URL = "https://timezone.abstractapi.com/"
    private val retrofisService:HoraService

    init {

        //Se instancia el Objeto retrofitService para poder hacer la consulta al WebService
        retrofisService = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())//Para pruebas, se obtiene en formato String toda la respuesta del WS
            .baseUrl(BASE_URL)
            .build()
            .create()
    }


    suspend fun conexionWS(localizacion:String)
    {

       try
       {
           //************** Para pruebas, se devuelven los datos del WS en formato String **************
           val resultadoWS = retrofisService.getHora("f54d2ec0c6cd44979a501f481dbf6027", localizacion)
           Log.e("Resultado WS Repositorio", resultadoWS)


       }catch(exception:Exception)
       {
           CoroutineScope(Dispatchers.Main).launch { Toast.makeText(context, R.string.errorConexionWS, Toast.LENGTH_LONG).show() }
       }

    }


}