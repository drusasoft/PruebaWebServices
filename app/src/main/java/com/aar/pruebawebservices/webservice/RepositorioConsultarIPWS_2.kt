package com.aar.pruebawebservices.webservice

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.aar.pruebawebservices.R
import com.aar.pruebawebservices.utils.DatosIP_2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create





class RepositorioConsultarIPWS_2(private val context: Context)
{

    //Ejemplo de Consulta: http://ip-api.com/json/80.26.226.49?fields=status,message,country,countryCode,region,regionName,city,zip,lat,lon,timezone,isp,org,asname,query
    private val URL_BASE = "http://ip-api.com/"
    private val FIELDS = "status,message,country,countryCode,region,regionName,city,zip,lat,lon,timezone,isp,org,asname,query"
    private val retrofitService:ConsultarIPservice_2

    //************************************ Variables Live Data *************************************
    private val _datosIPLive = MutableLiveData<DatosIPWS_2>()

    //Se usa la funcion map para transformar los datos devueltos en la consulta del WS al formato necesario para mostrarlos en la IU
    //En dicha funcion se hace una llamada a la funcion de extension definida en la clase DatosIPWS_2 que devuolve en un objeto de la clase DatosIP_2 con todos los datos en el formato correcto para mostrar en pantalla
    val datosIPLive: LiveData<DatosIP_2>
        get() = Transformations.map(_datosIPLive){ it.toDatosIP_2() }
    //********************************** Fin Variables Live Data ***********************************



    init {

        retrofitService = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            //.addConverterFactory(ScalarsConverterFactory.create())//Para pruebas, se obtiene en formato String toda la respuesta del WS
            .build()
            .create()
    }


    suspend fun conexionWS(direccionIP: String)
    {

        try {

            val resultadoWS = retrofitService.getDatosIP(direccionIP, FIELDS)

            //Para poder guardar los datos en una varible Livedata, solo se puede hacer en el hilo principal
            CoroutineScope(Dispatchers.Main).launch { _datosIPLive.value = resultadoWS }

        }catch(exception:Exception)
        {
            Toast.makeText(context, R.string.txtErrorConsultaWS, Toast.LENGTH_LONG).show()
        }

    }


    //se limpia el contenido de las variables LiveData
    fun limpiarVariablesLiveData()
    {
        _datosIPLive.value = DatosIPWS_2("--", "--", "--", "--", "--",
        "--", "--", 0.0, 0.0, "--", "--", "--", "--", "--")
    }

}