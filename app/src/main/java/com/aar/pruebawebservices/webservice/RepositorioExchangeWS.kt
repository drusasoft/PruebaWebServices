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




class RepositorioExchangeWS(private val context: Context)
{
    //Ejemplo de conexion al WS
    //http://api.currencylayer.com/convert?access_key=42d9ae313825287817c774303dbe566e&from=USD&to=EUR&amount=10

    private val URL_BASE = "http://api.currencylayer.com/"
    private val ACCESS_KEY = "42d9ae313825287817c774303dbe566e"
    private val retrofitService:ExchangeService


    //********************************* Variable LiveData ******************************************
    private val _resultadoExchangeLive = MutableLiveData<DatosExchangeWS>()

    val resultadoExchangeLive:LiveData<DatosExchangeWS>
        get() = _resultadoExchangeLive
    //******************************* Fin Variable LiveData ****************************************




    init {

        retrofitService = Retrofit.Builder()
        //.addConverterFactory(ScalarsConverterFactory.create())//para obtenr los resultados del WS en formato string
        .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL_BASE)
            .build()
            .create()
    }



    suspend fun conexionWS(divisaOrigen:String, divisaDestino:String, cantidad:String)
    {

        try {

            val resultadoWS = retrofitService.getExchange(ACCESS_KEY, divisaOrigen, divisaDestino, cantidad)

            CoroutineScope(Dispatchers.Main).launch { _resultadoExchangeLive.value = resultadoWS }

        }catch (exception:Exception)
        {
            CoroutineScope(Dispatchers.Main).launch {  Toast.makeText(context, R.string.errorConexionWS, Toast.LENGTH_LONG).show()  }
        }

    }


}