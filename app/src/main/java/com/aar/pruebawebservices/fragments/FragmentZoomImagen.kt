package com.aar.pruebawebservices.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aar.pruebawebservices.R
import com.aar.pruebawebservices.databinding.LayoutFragmentZoomImagenBinding
import com.squareup.picasso.Picasso


class FragmentZoomImagen:Fragment()
{

    private lateinit var urlImagen:String

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //Se obtiene la ruta de la imagen a mostrar de los argumentos pasados a este fragment en su llamada
        urlImagen = requireArguments().getString("UrlImagen", "")
        Log.e("URL Imagen",  urlImagen)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        val binding = LayoutFragmentZoomImagenBinding.inflate(inflater, container, false)

        Picasso.get()
            .load(urlImagen)
            .error(R.drawable.ic_error)
            .into(binding.photoViewZoom)

        return binding.root
    }

}