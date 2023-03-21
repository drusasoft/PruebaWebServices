package com.aar.pruebawebservices.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aar.pruebawebservices.databinding.LayoutFragmentDetallesHoraBinding
import com.aar.pruebawebservices.webservice.DatosHoraWS
import kotlin.concurrent.thread





class FragmentDetallesHora:Fragment()
{
    private lateinit var binding: LayoutFragmentDetallesHoraBinding
    private var datosHora:DatosHoraWS? = null

    //Variables usadas para actualizar la hora consultada
    private var finHiloHora = false
    private var hora = 0
    private var minutos = 0
    private var segundos = 0




    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //Se obtienen los datos sobre la Hora Consultada pasados a este Fragment como paramentros
        datosHora = arguments?.getParcelable("DatosHora")
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = LayoutFragmentDetallesHoraBinding.inflate(inflater, container, false)

        datosHora?.let {

            //Se obtiene los datos pasdos como parametro y se muestran en pantalla
            val fechaHora = it.datetime!!.split(" ")
            val fecha = fechaHora.get(0)
            val hora = fechaHora.get(1)

            binding.txtDate.text = fecha
            binding.txtTimeZone.text = it.timezone_name
            binding.txtTimeZoneLocation.text = it.timezone_location
            binding.txtGmtOffset.text = it.gmt_offset.toString()
            binding.txtLocation.text = it.requested_location

            //Se muestra la hora y se actualiza cada segundo (1000 ms) en un hilo aparte
            actualizarHoraWS(hora)

        }

        return binding.root
    }



    override fun onDestroy()
    {
        super.onDestroy()

        //Se finaliza el hilo donde se actualiza cada segundo la hora devuelta por el WS
        finHiloHora = true
    }



    //Se actualiza la hora recibida del WS para asi dar la sensacion de que se muestra en directo
    private fun actualizarHoraWS(horaWS:String)
    {
        //Se obtiene la hora, minutos y segundo de la hora Obtenida del WS
        val horaSplit = horaWS.split(":")

        hora = horaSplit.get(0).toInt()
        minutos = horaSplit.get(1).toInt()
        segundos = horaSplit.get(2).toInt()

        //la actualizacion de la Hora cada segundo se realiza en un hilo en segundo plano.
        //En este hilo simplemente hay un bucle que incrementa la hora devuelta por el WS 1 segundo y despues duerme durante 1000 ms

        thread {

            while (!finHiloHora) {

                while (!finHiloHora) {
                    //Se incrementa la hora del rejoj personalziado 1 segundo y se actualiza la hora y minutos si corresponde
                    segundos++

                    if (segundos == 60) {
                        segundos = 0
                        minutos++

                        if (minutos == 60) {
                            minutos = 0
                            hora++

                            if (hora == 24)
                                hora = 0
                        }
                    }

                    //Se muestra la Hora actualizada en pantalla
                    mostrarHoraWS()

                    Thread.sleep(1000)//Se duerem el hilo durante 1 segundo
                }

            }

        }//**** Fin Hilo

    }


    //Se muestra en pantalla la Hora Actualizada obtenida del WS
    private fun mostrarHoraWS()
    {
        var horaString = ""
        var minutosString = ""
        var segundosString = ""

        if(hora < 10) horaString = "0${hora}" else horaString = hora.toString()
        if(minutos < 10) minutosString = "0${minutos}" else minutosString = minutos.toString()
        if(segundos < 10) segundosString = "0${segundos}" else segundosString = segundos.toString()

        binding.txtRelojDigital.text = "${horaString}:${minutosString}:${segundosString}"
    }


}