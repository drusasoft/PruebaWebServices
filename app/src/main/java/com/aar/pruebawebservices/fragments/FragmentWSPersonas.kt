package com.aar.pruebawebservices.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.aar.pruebawebservices.R
import com.aar.pruebawebservices.adapters.PersonasAdapter
import com.aar.pruebawebservices.databinding.LayoutFragmentWsPersonasBinding
import com.aar.pruebawebservices.models.FragmentWSPersonasModel
import com.aar.pruebawebservices.models_factory.FragmentWSPersonasModelFactory
import com.aar.pruebawebservices.utils.PruebaWebServices





class FragmentWSPersonas: Fragment()
{

    //se instacia el ViewModel para este Fragment
    private val model:FragmentWSPersonasModel by viewModels{ FragmentWSPersonasModelFactory(requireContext(), (requireActivity().applicationContext as PruebaWebServices).getPersonaDataBase()) }

    private lateinit var adapterRecycler: PersonasAdapter
    private lateinit var navController: NavController




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        val binding = LayoutFragmentWsPersonasBinding.inflate(inflater, container, false)

        //Se instancia el RecyclerView y su Adapter
        adapterRecycler = PersonasAdapter({personaDB, elemPulsado->

            //lambda que se ejecuta cuando se hace una pulsacion sobre un elemeto del Recycler

            val bundle = Bundle()

            if(elemPulsado == "imageViewRecyclerFoto")
            {
                //Si se pulsa solo sobre el ImageView de la foto, se navega al Fragment ZoomImagen
                bundle.putString("UrlImagen", personaDB.foto)
                navController.navigate(R.id.irFragmentZoomImagen, bundle)
            }
            else
            {
                //Se navega al frgament que muestra todos los datos de la Persona
                bundle.putParcelable("DatosPersona", personaDB)
                navController.navigate(R.id.irFragmentDetallesPersona, bundle)
            }


        },{

            //lambda que se ejecuta cuando se hace una pulsacion larga sobre un elemeto del Recycler
            Toast.makeText(requireContext(), "Pulsacion Larga", Toast.LENGTH_LONG).show()

            true
        })

        binding.recyclerPersonas.adapter = adapterRecycler

        //************************************* ClickListeners *************************************

        binding.btnConexionWS.setOnClickListener { model.conexionWS() }

        //*********************************** Fin ClickListeners ***********************************


        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        //Se instancia el objeto NavController
        navController = Navigation.findNavController(view)

        //Se declaran los Observers para las variables LiveData
        setObservers()
    }



    override fun onDestroyView()
    {
        super.onDestroyView()

        //Al salir del fragment se deseleccionan todos los elementos que estuvieran seleccionados de  la BD
        model.deseleccionarTodos()
    }



    //Se registran los Observers para las variables LiveData definidas en el Model
    private fun setObservers()
    {

        model.personasBDLive.observe(viewLifecycleOwner){listaPersonasBD->

            Log.e("Personad BD Live", "${listaPersonasBD}")
            //Se actualiza el Adapter del RecyclerView para mostrar los datos
            adapterRecycler.submitList(listaPersonasBD)

        }

    }

}