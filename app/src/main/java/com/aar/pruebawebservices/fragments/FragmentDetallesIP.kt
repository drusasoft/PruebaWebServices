package com.aar.pruebawebservices.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aar.pruebawebservices.databinding.LayoutFragmentDetallesIpBinding
import com.aar.pruebawebservices.webservice.DatosIPWS
import com.squareup.picasso.Picasso


class FragmentDetallesIP:Fragment()
{

    private lateinit var binding: LayoutFragmentDetallesIpBinding
    private var direccionIP:String? = null
    private var datosIP:DatosIPWS? = null




    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //Se obtienen todos los datos sobre la IP consultada al WS, pasados como parametro a este fragment
        direccionIP = arguments?.getString("DireccionIP")
        datosIP = arguments?.getParcelable("DatosIP")
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = LayoutFragmentDetallesIpBinding.inflate(inflater, container, false)

        //Se en al IU todos los datos sobre la IP consultada, psados como parametros a este Frgament
        datosIP?.let {

            Picasso.get().load(it.flag.png).into(binding.imageViewBandera)

            binding.txtDireccionIP.text = direccionIP
            binding.txtCity.text = it.city
            binding.txtRegion.text = it.region
            binding.txtCountry.text = it.country
            binding.txtLat.text = it.latitude.toString()
            binding.txtLng.text = it.longitude.toString()

        }

        return binding.root
    }


}