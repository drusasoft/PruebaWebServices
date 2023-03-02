package com.aar.pruebawebservices.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aar.pruebawebservices.databinding.LayoutFragmentConsultarIpBinding
import com.aar.pruebawebservices.models.FragmentWSConsultarIPModel





class FragmentWSConsultarIP:Fragment()
{
    private lateinit var binding: LayoutFragmentConsultarIpBinding
    private val model:FragmentWSConsultarIPModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = LayoutFragmentConsultarIpBinding.inflate(inflater, container, false)

        binding.btnConexionWS.setOnClickListener { model.pruebaConectarWSIP() }

        return binding.root
    }
}