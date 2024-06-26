package com.aar.pruebawebservices.webservice

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.aar.pruebawebservices.R
import com.aar.pruebawebservices.utils.DatosIP
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create




class RepositorioConsultarIPWS(private val context: Context)
{
    //Ej de URL = "https://ipgeolocation.abstractapi.com/v1/?api_key=cb71b89ab513414b920bff9be723c36c&ip_address=166.171.248.255"
    private val URL_BASE = "https://ipgeolocation.abstractapi.com/"
    private val retrofitService:ConsultarIPService

    //Variable del tipo LiveData que contendra los datos sobre la IP
    val datosIPLive = MutableLiveData<DatosIP>()


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

        try
        {
            //Se consulta en el WS, los datos de la IP pasada como parametro
            val resultadoWS = retrofitService.getDatosIP("cb71b89ab513414b920bff9be723c36c", direccionIP)

            //Para poder guardar los datos en una varible Livedata, solo se puede hacer en el hilo principal
            CoroutineScope(Dispatchers.Main).launch {

                datosIPLive.value = resultadoWS.toDatosIP()//Se llama al funcion de Extension que he definido, para convertir los datos a la estructura usada por el FragmentDetallesIP
            }

        }catch (exception:Exception)
        {
            //Para poder mostrar un mensaje Toast solo se puede hacer en el hilo principal
            CoroutineScope(Dispatchers.Main).launch { Toast.makeText(context, R.string.txtErrorConsultaWS, Toast.LENGTH_LONG).show() }
        }

    }


}