package com.aar.pruebawebservices.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.aar.pruebawebservices.databinding.LayoutFragmentDetallesConversorMonedaBinding





class FragmentDetallesConversorMoneda: Fragment()
{

    private lateinit var binding:LayoutFragmentDetallesConversorMonedaBinding

    private lateinit var navController: NavController



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = LayoutFragmentDetallesConversorMonedaBinding.inflate(inflater, container, false)



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        //Se instancia el objeto NavController
        navController = Navigation.findNavController(view)
    }


}