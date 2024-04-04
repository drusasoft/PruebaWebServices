package com.aar.pruebawebservices.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aar.pruebawebservices.R
import com.aar.pruebawebservices.databinding.LayoutFragmentConsultarExchangeBinding
import com.aar.pruebawebservices.models.FragmentWSExchangeModel
import com.aar.pruebawebservices.models_factory.FragmentWSExchangeModelFactory





class FragmentWSExchange: Fragment()
{

    private lateinit var binding: LayoutFragmentConsultarExchangeBinding
    private val model by viewModels<FragmentWSExchangeModel>{ FragmentWSExchangeModelFactory(requireContext()) }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {

        binding = LayoutFragmentConsultarExchangeBinding.inflate(inflater,container, false)


        //********************************* Configuracion Spinners *********************************

        binding.spinnerMonedaOrigen.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.array_monedas, android.R.layout.simple_spinner_item)
            .also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        binding.spinnerMonedaDestino.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.array_monedas, android.R.layout.simple_spinner_item)
            .also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        //********************************* Fin Configuracion Spinners *********************************



        //************************************* ClickListeners *************************************

        binding.btnExchange.setOnClickListener {

            if(!binding.edittextCantidad.text.isNullOrEmpty())
            {
                //se comprueba que no esten seleccionada la misma divisa en los spinners
                if(binding.spinnerMonedaOrigen.selectedItemPosition != binding.spinnerMonedaDestino.selectedItemPosition)
                {
                    //Se obtiene las siglas de las divisas seleccionadas
                    val divisaOrigen = binding.spinnerMonedaOrigen.selectedItem.toString().split(",").get(0)
                    val divisaDestino = binding.spinnerMonedaDestino.selectedItem.toString().split(",").get(0)

                    val cantidad = binding.edittextCantidad.text.toString()

                    model.conexionExchangeWS(divisaOrigen, divisaDestino, cantidad)

                }else
                {
                    Toast.makeText(requireContext(), R.string.txtErrorseleccionMoneda, Toast.LENGTH_LONG).show()
                }

            }else
            {
                Toast.makeText(requireContext(), R.string.txtErrorCantidadMoneda, Toast.LENGTH_LONG).show()
            }

        }

        //*********************************** Fin ClickListeners ***********************************


        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        //Se declaran los Observers para las variables LiveData
        setObservers()
    }



    //Se declaran los Observers para las variables LiveData definidas en el ViewModel
    private fun setObservers()
    {

        model.resultadoExchangeLive.observe(viewLifecycleOwner){resultadoExchange->

            if(resultadoExchange.success)
            {
                val divisa = binding.spinnerMonedaDestino.selectedItem.toString().split(",").get(0)
                binding.txtResultadoExchange.text = "${resultadoExchange.result} ${divisa}"
                binding.txtRelacionCambio.text = resultadoExchange.info.quote.toString()

            }else
            {
                Toast.makeText(requireContext(), R.string.txtErrorExchange, Toast.LENGTH_LONG).show()
            }

        }

    }

}