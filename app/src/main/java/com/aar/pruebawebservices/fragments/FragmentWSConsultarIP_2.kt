package com.aar.pruebawebservices.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aar.pruebawebservices.R
import com.aar.pruebawebservices.databinding.LayoutFragmentConsultarIp2Binding
import com.aar.pruebawebservices.models.FragmentWSConsultarIP2Model
import com.aar.pruebawebservices.models_factory.FragmentWSConsultarIP2ModelFactory





class FragmentWSConsultarIP_2: Fragment()
{

    private lateinit var binding: LayoutFragmentConsultarIp2Binding
    private val model by viewModels<FragmentWSConsultarIP2Model> { FragmentWSConsultarIP2ModelFactory(requireContext()) }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = LayoutFragmentConsultarIp2Binding.inflate(inflater, container, false)
        binding.model = model//Se asocia el ViewModel con el XML de IU para asi actualizar el contenido de algun View del XML con el valor de alguna de la variables LiveData del ViewModel (Y me ahorro codigo)
        binding.lifecycleOwner = this//Para que los datos del ViewModel se puedan mostrar directamente en el XML



        //************************************* ClickListeners *************************************

        binding.btnConexionWS.setOnClickListener {

            //Se comprueba que el campo de texto no este vacio
            if(!binding.editTextIP.text.isNullOrEmpty() && !binding.editTextIP.text.isNullOrBlank())
            {
                model.realizarConexionWS(binding.editTextIP.text.toString().trim())
            }else
            {
                Toast.makeText(requireContext(), R.string.txtErrorInsertarIP, Toast.LENGTH_LONG).show()
            }

        }

        //*********************************** Fin ClickListeners ***********************************


        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        //Se registran los Observers para las variables LiveData
        setObservers()
    }



    //Se declaran los Observers para las variables LiveData definidas en el ViewModel
    private fun setObservers()
    {

        model.datosIPLive.observe(viewLifecycleOwner){datosIP->

            if(datosIP.status == "fail")
                Toast.makeText(requireContext(), R.string.txtNoDatosConsultaIP, Toast.LENGTH_LONG).show()

        }

    }

}