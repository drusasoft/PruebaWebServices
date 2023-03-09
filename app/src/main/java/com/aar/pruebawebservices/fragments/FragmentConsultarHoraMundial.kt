package com.aar.pruebawebservices.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aar.pruebawebservices.databinding.LayoutFragmentConsultarHoraMundialBinding




class FragmentConsultarHoraMundial: Fragment()
{

    private lateinit var binding: LayoutFragmentConsultarHoraMundialBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = LayoutFragmentConsultarHoraMundialBinding.inflate(inflater, container, false)

        return binding.root
    }

}