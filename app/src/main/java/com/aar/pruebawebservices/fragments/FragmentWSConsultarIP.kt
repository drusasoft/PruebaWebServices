package com.aar.pruebawebservices.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.aar.pruebawebservices.R
import com.aar.pruebawebservices.databinding.LayoutFragmentConsultarIpBinding
import com.aar.pruebawebservices.models.FragmentWSConsultarIPModel
import com.aar.pruebawebservices.models_factory.FragmentWSConsultarIPModelFactory


class FragmentWSConsultarIP:Fragment()
{

    private lateinit var binding: LayoutFragmentConsultarIpBinding
    private val model:FragmentWSConsultarIPModel by viewModels{ FragmentWSConsultarIPModelFactory(requireContext())}
    private lateinit var navController: NavController



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = LayoutFragmentConsultarIpBinding.inflate(inflater, container, false)

        //************************************* ClickListeners *************************************

        binding.btnConexionWS.setOnClickListener {

            if(!binding.editTextIP.text.isNullOrEmpty())
            {
                val direccionIP = binding.editTextIP.text.toString().trim()
                model.conectarWSIP(direccionIP)

            }else
            {
                Toast.makeText(requireContext(), "Debes introducir una direccion IP", Toast.LENGTH_LONG).show()
            }

        }

        //*********************************** Fin ClickListeners ***********************************

            return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        //Se declaran los Observers para las variables LiveData
        setObservers()

        //Se instancia el Objeto NavController
        navController = Navigation.findNavController(view)
    }



    //Se declaran los Observers para las variables LiveData definidas en el ViewModel
    private fun setObservers()
    {

        //Observer para la Varible LiveData que contiene todos los datos devueltos por el WS sobre la direccion IP Consultada
        model.datosWSLive.observe(viewLifecycleOwner){datosIP->

            Log.e("Datos IP", "${datosIP}")

            //Se comprueba si el WS ha devuelto datos para IP Consultada
            if(datosIP.direccion_ip != "--")
            {
                val direccionIP = datosIP.direccion_ip

                //Se navega al Fragment DetallesIP pasandole como parametro los datos devueltos por el WS
                val bundle = Bundle()
                bundle.putString("DireccionIP", direccionIP)
                bundle.putParcelable("DatosIP", datosIP)
                navController.navigate(R.id.irFragmentDetallesIP, bundle)

                binding.editTextIP.text = null
                model.limpiarVariablesLiveData()
            }

        }

    }


}