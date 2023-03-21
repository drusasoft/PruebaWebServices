package com.aar.pruebawebservices.models

import android.content.Context
import android.location.Geocoder
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.aar.pruebawebservices.R
import com.aar.pruebawebservices.webservice.HoraService
import com.aar.pruebawebservices.webservice.RepositorioConsultarHoraWS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import java.util.*


class FragmentWSConsultarHoraModel(private val context: Context):ViewModel()
{

    //Repositorio deonde se realzia la Conexion con el WS
    private val repositorioConsultarHoraWS = RepositorioConsultarHoraWS(context)

    //************************************ Coroutina en Hilo IO ************************************
    private val viewModelJob = Job()
    private val coroutineScopeIO = CoroutineScope(viewModelJob + Dispatchers.IO)
    //********************************** Fin Coroutina en Hilo IO **********************************

    //************************************* Variables LiveData *************************************
    val datosWSLive = repositorioConsultarHoraWS.datosHoraLive
    //*********************************** Fin Variables LiveData ***********************************




    fun limpiarVariablesLiveData() { datosWSLive.value = null }



    //Se realiza la conexion con WS
    fun conexionWS(localidad:String, pais:String)
    {
        //Antes de consultar la hora en el WS de la localidad introducida por el usuario, se comprueba que dicha localidad existe en Geocoder
        val geocoder = Geocoder(context, Locale.getDefault())

        try
        {
            val listaLocalidades = geocoder.getFromLocationName("${localidad}\n${pais}", 1)

            if(listaLocalidades.size > 0)
            {
                coroutineScopeIO.launch { repositorioConsultarHoraWS.conexionWS("${localidad}, ${pais}") }

            }else
            {
                Toast.makeText(context, R.string.txtNoExisteLocalidad, Toast.LENGTH_LONG).show()
            }

        }catch (exception:Exception)
        {
            Toast.makeText(context, R.string.txtExceptionGeocoder, Toast.LENGTH_LONG).show()
        }

    }

}