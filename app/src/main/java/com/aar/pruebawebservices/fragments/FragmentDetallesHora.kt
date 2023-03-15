package com.aar.pruebawebservices.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aar.pruebawebservices.databinding.LayoutFragmentDetallesHoraBinding
import com.aar.pruebawebservices.webservice.DatosHoraWS





class FragmentDetallesHora:Fragment()
{
    private lateinit var binding: LayoutFragmentDetallesHoraBinding
    private var datosHora:DatosHoraWS? = null



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

        }

        return binding.root
    }


}