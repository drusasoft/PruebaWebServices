package com.aar.pruebawebservices.models_factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aar.pruebawebservices.models.FragmentWSConsultarHoraModel

@Suppress("UNCHECKED_CAST")
class FragmentWSConsultarHoraModelFactory(private val context: Context):ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if(modelClass.isAssignableFrom(FragmentWSConsultarHoraModel::class.java))
            return FragmentWSConsultarHoraModel(context) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}