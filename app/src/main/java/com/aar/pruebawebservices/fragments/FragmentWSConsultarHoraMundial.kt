package com.aar.pruebawebservices.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aar.pruebawebservices.databinding.LayoutFragmentConsultarHoraMundialBinding
import com.aar.pruebawebservices.models.FragmentWSConsultarHoraModel
import com.aar.pruebawebservices.models_factory.FragmentWSConsultarHoraModelFactory





class FragmentWSConsultarHoraMundial: Fragment()
{

    private lateinit var binding: LayoutFragmentConsultarHoraMundialBinding
    private val model:FragmentWSConsultarHoraModel by viewModels{ FragmentWSConsultarHoraModelFactory(requireContext()) }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = LayoutFragmentConsultarHoraMundialBinding.inflate(inflater, container, false)


        //************************************* ClickListeners *************************************

        binding.btnConexionWS.setOnClickListener {

            //****** Para Pruebas
            val localziacion = "Tokyo, Japan"
            model.conexionWS(localziacion)

        }

        //*********************************** Fin ClickListeners ***********************************


        return binding.root

    }

}