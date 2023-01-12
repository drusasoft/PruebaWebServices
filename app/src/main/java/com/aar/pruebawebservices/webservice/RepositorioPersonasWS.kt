package com.aar.pruebawebservices.webservice

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.aar.pruebawebservices.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.Exception





class RepositorioPersonasWS(private val context: Context)
{
    //URL Ejemplo = https://randomuser.me/api/?results=10
    private val URL_BASE = "https://randomuser.me/"

    private val retrofitService:PersonaService

    init {
      retrofitService = Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create())
          .baseUrl(URL_BASE)
          .build()
          .create()
    }


    suspend fun conexionWS(numResultados:String)
    {

        try {

            //Se llama al metodo definido en la interfaz PersonaService que hace la peticion al WS y devueve los resultados mapeados en un Objeto del tipo DatosPersonaWS
            val datosPersonasWS = retrofitService.getPersonas(numResultados)

            Log.e("Datos Personas", "${datosPersonasWS.results}")

        }catch (exception:Exception)
        {
            //Los mensajes Toast solo se pueden ejecutar en el hilo principal por eso lo meto dentro de esta Coroutina
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(context, R.string.errorConexionWS, Toast.LENGTH_LONG).show()
                Log.e("Excepcion WS", exception.toString())
            }

        }

    }

}