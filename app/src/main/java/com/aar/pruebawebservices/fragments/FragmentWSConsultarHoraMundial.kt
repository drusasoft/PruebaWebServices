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
import com.aar.pruebawebservices.R
import com.aar.pruebawebservices.databinding.LayoutFragmentConsultarHoraMundialBinding
import com.aar.pruebawebservices.models.FragmentWSConsultarHoraModel
import com.aar.pruebawebservices.models_factory.FragmentWSConsultarHoraModelFactory
import kotlin.concurrent.thread


class FragmentWSConsultarHoraMundial: Fragment()
{

    private lateinit var binding: LayoutFragmentConsultarHoraMundialBinding
    private val model:FragmentWSConsultarHoraModel by viewModels{ FragmentWSConsultarHoraModelFactory(requireContext()) }

    private lateinit var navController: NavController

    //******** Variable para la prueba de Reloj
    private var finReloj = false
    private var hora = 0
    private var minutos = 0
    private var segundos = 0



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = LayoutFragmentConsultarHoraMundialBinding.inflate(inflater, container, false)


        //************************************* ClickListeners *************************************

        binding.btnConexionWS.setOnClickListener {


            //********* Para Pruebas
            //model.conexionWS("Tokyo, Japan")

            if(!binding.editTextLocalidad.text.isNullOrEmpty() && !binding.editTextPais.text.isNullOrEmpty())
            {
                val localidad = binding.editTextLocalidad.text.toString().trim()
                val pais = binding.editTextPais.text.toString().trim()

                model.conexionWS(localidad, pais)

            }else
            {
                Toast.makeText(requireContext(), R.string.txtErrorInsertarDatos, Toast.LENGTH_LONG).show()
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

        //Observer para la Varible LiveData que contiene todos los datos devueltos por el WS sobre la hora actual de una Localidad
        model.datosWSLive.observe(viewLifecycleOwner){datosHora->

            //Se comprueba que la variable LiveData con los datos obtenidos del WS es distinta de null
            datosHora?.let {

                //Se comprueba que en los datos devueltos por el WS, el campo datetime no sea nulo
                if(it.datetime != null)
                {
                    //Se navega al Fragment DetallesHora pasando como parametro todos los datos obtenidos del WS
                    val bundle = Bundle()
                    bundle.putParcelable("DatosHora", it)

                    navController.navigate(R.id.irFragmentDetallesHora, bundle)

                    //Se borra el contenido de la variable LiveData para evitar que al volver a este Fragment se vuelva a navegar de forma automatica al Fragment DetallesHora
                    model.limpiarVariablesLiveData()

                    //Se limpian los EditText
                    binding.editTextPais.setText("")
                    binding.editTextLocalidad.setText("")

                }else
                {
                    Toast.makeText(context, R.string.txtErrorLocalidadWS, Toast.LENGTH_LONG).show()
                }

            }

        }

    }



}