package com.aar.pruebawebservices.models_factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aar.pruebawebservices.models.FragmentWSConsultarIP2Model

@Suppress("UNCHECKED_CAST")
class FragmentWSConsultarIP2ModelFactory(private val context: Context):ViewModelProvider.Factory
{

    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if(modelClass.isAssignableFrom(FragmentWSConsultarIP2Model::class.java))
            return FragmentWSConsultarIP2Model(context) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}