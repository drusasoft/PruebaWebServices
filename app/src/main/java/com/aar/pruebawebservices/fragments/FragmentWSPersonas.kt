package com.aar.pruebawebservices.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.aar.pruebawebservices.databinding.LayoutFragmentWsPersonasBinding
import com.aar.pruebawebservices.models.FragmentWSPersonasModel
import com.aar.pruebawebservices.models_factory.FragmentWSPersonasModelFactory


class FragmentWSPersonas: Fragment()
{

    //se instacia el ViewModel para este Fragment
    private val model:FragmentWSPersonasModel by viewModels{ FragmentWSPersonasModelFactory(requireContext()) }

    private lateinit var navController: NavController




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        val binding = LayoutFragmentWsPersonasBinding.inflate(inflater, container, false)


        //************************************* ClickListeners *************************************

        binding.btnConexionWS.setOnClickListener { model.conexionWS() }

        //*********************************** Fin ClickListeners ***********************************


        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        //Se instancia el objeto NavController
        navController = Navigation.findNavController(view)
    }

}