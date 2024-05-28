package com.aar.pruebawebservices.models

import android.content.Context
import androidx.lifecycle.ViewModel
import com.aar.pruebawebservices.webservice.RepositorioConsultarIPWS_2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch





class FragmentWSConsultarIP2Model(private val context: Context):ViewModel()
{
    //Repositorio deonde se realzia la Conexion con el WS
    private val repositorioConsultarIPWS = RepositorioConsultarIPWS_2(context)

    //************************************ Coroutina en Hilo IO ************************************
    private val viewModelJob = Job()
    private val coroutineScopeIO = CoroutineScope(viewModelJob + Dispatchers.IO)
    //********************************** Fin Coroutina en Hilo IO **********************************

    //************************************* Variables LiveData *************************************
    val datosIPLive = repositorioConsultarIPWS.datosIPLive
    //*********************************** Fin Variables LiveData ***********************************



    override fun onCleared()
    {
        super.onCleared()

        //Se cancela el Job de la Coroutina donde se ejecuta Retrofit
        viewModelJob.cancel()
    }



    //Se realiza la COnexion con el WS para obtener todos los datos de la Direccion IP pasada como parametro
    fun realizarConexionWS(direccionIP:String)
    {
        //Antes de hacer la consulta al WS, se limpia el contenido de la variable LiveData de consultas anteriores
        repositorioConsultarIPWS.limpiarVariablesLiveData()

        coroutineScopeIO.launch { repositorioConsultarIPWS.conexionWS(direccionIP) }
    }

}