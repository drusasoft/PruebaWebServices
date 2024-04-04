package com.aar.pruebawebservices.webservice

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aar.pruebawebservices.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create





class RepositorioConversorMoneda(private val context:Context)
{
    //Ejemplo URL para conseguir el cambio de Dolares a Euros
    //https://exchange-rates.abstractapi.com/v1/live?api_key=5c6bad581ec74fed8fdb81edd15b99be&base=USD&target=EUR

    private val URL_BASE = "https://exchange-rates.abstractapi.com/"
    private val retrofitService:MonedaService

    //************************************* Variables LiveData *************************************
    private val _cambioActualLive = MutableLiveData<Float?>()

    val cambioActualLive:LiveData<Float?>
        get() = _cambioActualLive
    //*********************************** Fin Variables LiveData ***********************************



    init {

        retrofitService = Retrofit.Builder()
            //.addConverterFactory(ScalarsConverterFactory.create())//Para pruebas, se obtiene en formato String toda la respuesta del WS)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL_BASE)
            .build()
            .create()
    }



    suspend fun conectarWS(base:String, target:String)
    {

        try {

            //******** Para Pruebas obtengo el resultado del WS en formato String ********
            //val resultadoString = retrofitService.getConversion("5c6bad581ec74fed8fdb81edd15b99be", base, target)
            //Log.e("Resultado WS", resultadoString)

            val datosMonedasWS = retrofitService.getConversion("5c6bad581ec74fed8fdb81edd15b99be", base, target)
            Log.e("Resultado WS", "${datosMonedasWS}")

            //Se llama a la funcion de extension que he definido en el data class DatosMonedasWS para obtener el valor de cambio entre las monedas solicitadas por el usuario
            val valorCambio = datosMonedasWS.toGetValorCambio()

            //Se guarda el valor de cambio obtnido del WS en la Variable LiveData, el valor de las variables LiveData solo se puede modificar en el hilo principal
            CoroutineScope(Dispatchers.Main).launch { _cambioActualLive.value = valorCambio }

        }catch(exception:Exception)
        {
            //Los mensajes Toast solo se pueden mostrar en el hilo principal
            CoroutineScope(Dispatchers.Main).launch { Toast.makeText(context, R.string.txtErrorConsultaWS, Toast.LENGTH_LONG).show() }
        }

    }



    //Se limpia el contenenido de las variables LiveData
    fun limpiarVariablesLiveData() { _cambioActualLive.value = null }


}