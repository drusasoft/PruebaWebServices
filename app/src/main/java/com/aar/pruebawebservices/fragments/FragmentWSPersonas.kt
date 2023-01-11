package com.aar.pruebawebservices.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aar.pruebawebservices.databinding.LayoutFragmentWsPersonasBinding
import com.aar.pruebawebservices.models.FragmentWSPersonasModel





class FragmentWSPersonas: Fragment()
{

    //se instacia el ViewModel para este Fragment
    private val model:FragmentWSPersonasModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        val binding = LayoutFragmentWsPersonasBinding.inflate(inflater, container, false)

        //******prueba
        model.pruebaCulera()

        return binding.root
    }

}