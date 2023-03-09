package com.aar.pruebawebservices.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aar.pruebawebservices.databinding.LayoutFragmentDetallesIpBinding
import com.aar.pruebawebservices.utils.DatosIP
import com.squareup.picasso.Picasso





class FragmentDetallesIP:Fragment()
{

    private lateinit var binding: LayoutFragmentDetallesIpBinding
    private var direccionIP:String? = null
    private var datosIP: DatosIP? = null




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

        //Se muestra en al IU todos los datos sobre la IP consultada, pasados como parametros a este Frgament
        datosIP?.let {

            Picasso.get().load(it.bandera).into(binding.imageViewBandera)

            binding.txtDireccionIP.text = direccionIP
            binding.txtCity.text = it.ciudad
            binding.txtRegion.text = it.region
            binding.txtCountry.text = it.pais
            binding.txtLat.text = it.latitud
            binding.txtLng.text = it.longitud

        }

        return binding.root
    }


}