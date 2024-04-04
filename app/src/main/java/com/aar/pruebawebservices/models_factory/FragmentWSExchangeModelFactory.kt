package com.aar.pruebawebservices.models_factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aar.pruebawebservices.models.FragmentWSExchangeModel

class FragmentWSExchangeModelFactory(private val context: Context): ViewModelProvider.Factory
{

    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if(modelClass.isAssignableFrom(FragmentWSExchangeModel::class.java))
            return FragmentWSExchangeModel(context) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}