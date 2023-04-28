package com.aar.pruebawebservices.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.aar.pruebawebservices.R
import com.aar.pruebawebservices.databinding.LayoutFragmentConversorMonedaBinding





class FragmentWSConversorMoneda:Fragment()
{

    private lateinit var binding:LayoutFragmentConversorMonedaBinding

    private lateinit var navController: NavController




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {

        binding = LayoutFragmentConversorMonedaBinding.inflate(inflater, container, false)


        cargarDatosSpinner()

        //************************ ClickListener ************************
        binding.btnConversion.setOnClickListener {

            if(binding.spinnerMonedaOrigen.selectedItemPosition != 0 && binding.spinnerMonedaDestino.selectedItemPosition != 0)
            {

                if(!binding.edittextCantidad.text.isNullOrEmpty())
                {

                }else
                {
                    Toast.makeText(requireContext(), "Debes insertar la cantidad a convertir", Toast.LENGTH_LONG).show()
                }

            }else
            {
                Toast.makeText(requireContext(), "Debes Seleccionar la Moneda de Origen y Destino del cambio", Toast.LENGTH_LONG).show()
            }
        }
        //************************ Fin ClickListener ************************


        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        //Se instancia el objeto NavController
        navController = Navigation.findNavController(view)
    }



    //Se cargan en los Spinner todas las monedas disponibles para la conversion
    private fun cargarDatosSpinner()
    {

        //Se crea la lista de monedas a partir del array definido en los recursos
        val listaMonedas = mutableListOf<String>()
        listaMonedas.add("Selecciona Moneda...")
        listaMonedas.addAll(requireContext().resources.getStringArray(R.array.array_monedas))

        //Se cargan los spinner con los datos de la lista de monedas
        ArrayAdapter(requireContext(), R.layout.layout_spinner, listaMonedas)
            .also {
                it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerMonedaOrigen.adapter = it
                binding.spinnerMonedaDestino.adapter = it
            }

    }


}