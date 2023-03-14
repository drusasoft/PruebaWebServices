package com.aar.pruebawebservices.models_factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aar.pruebawebservices.models.FragmentWSConsultarIPModel

@Suppress("UNCHECKED_CAST")
class FragmentWSConsultarIPModelFactory(private val context:Context):ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(FragmentWSConsultarIPModel::class.java))
            return FragmentWSConsultarIPModel(context) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}