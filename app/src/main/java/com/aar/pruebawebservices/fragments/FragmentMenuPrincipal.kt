package com.aar.pruebawebservices.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.aar.pruebawebservices.R
import com.aar.pruebawebservices.databinding.LayoutFragmentMenuPrincipalBinding





class FragmentMenuPrincipal: Fragment()
{

    private lateinit var navController: NavController


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        val binding = LayoutFragmentMenuPrincipalBinding.inflate(inflater, container, false)


        //************************************* ClickListeners *************************************

        binding.txtOpcionWSPersonas.setOnClickListener { navController.navigate(R.id.irFragmentWSPersonas) }
        binding.txtOpcionWSConsultarIP.setOnClickListener { navController.navigate(R.id.irFragmentWSConsultarIP) }
        binding.txtOpcionWSConsultarHora.setOnClickListener { navController.navigate(R.id.irFragmentConsultarHoraMundial) }

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