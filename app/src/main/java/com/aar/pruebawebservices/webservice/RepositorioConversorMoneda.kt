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





class RepositorioConversorMoneda(private val context:Context)
{
    //Ejemplo URL para conseguir el cambio de Dolares a Euros
    //https://exchange-rates.abstractapi.com/v1/live?api_key=5c6bad581ec74fed8fdb81edd15b99be&base=USD&target=EUR

    private val URL_BASE = "https://exchange-rates.abstractapi.com/"
    private val retrofitService:MonedaService


    init {

        retrofitService = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())//Para pruebas, se obtiene en formato String toda la respuesta del WS)
            .baseUrl(URL_BASE)
            .build()
            .create()
    }



    suspend fun conectarWS(base:String, target:String)
    {

        try {

            //Para Pruebas obtengo el resultado del WS en formato String
            val resultadoString = retrofitService.getConversion("5c6bad581ec74fed8fdb81edd15b99be", base, target)
            Log.e("Resultado WS", resultadoString)

        }catch(exception:Exception)
        {
            //Los mensajes Toast solo se pueden mostrar en el hilo principal
            CoroutineScope(Dispatchers.Main).launch { Toast.makeText(context, R.string.txtErrorConsultaWS, Toast.LENGTH_LONG).show() }
        }

    }

}