package com.aar.pruebawebservices.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aar.pruebawebservices.databinding.LayoutFragmentDetallesIpBinding
import com.aar.pruebawebservices.webservice.DatosIPWS





class FragmentDetallesIP:Fragment()
{

    private lateinit var binding: LayoutFragmentDetallesIpBinding
    private var datosIP:DatosIPWS? = null




    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //Se obtienen todos los datos sobre la IP consultada al WS, pasados como parametro a este fragment
        //datosIP = arguments?.getParcelable("DatosIP")
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = LayoutFragmentDetallesIpBinding.inflate(inflater, container, false)



        return binding.root
    }


}