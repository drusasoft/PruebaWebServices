package com.aar.pruebawebservices.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.aar.pruebawebservices.R
import com.aar.pruebawebservices.database.PersonaDB
import com.aar.pruebawebservices.databinding.LayoutFragmentDetallesPersonaBinding
import com.squareup.picasso.Picasso





class FragmentDetallesPersona: Fragment()
{

    private lateinit var navController: NavController
    private var datosPersona:PersonaDB? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Se obtiene los datos de la persona pasados como argumento a llamar a este fragment
        datosPersona = requireArguments().getParcelable<PersonaDB>("DatosPersona")
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        val binding = LayoutFragmentDetallesPersonaBinding.inflate(inflater, container, false)

        //Se muestran todos los datos de la persona que se han pasado como argumento al Fragment
        datosPersona?.let {datos->

            Picasso.get()
                .load(datos.foto)
                .error(R.drawable.ic_error)
                .into(binding.imageFoto)

            binding.txtNombre.text = "${datos.nombre} ${datos.apellidos}"
            binding.txtUbicacion.text = "${datos.ciudad}, ${datos.pais}"
            binding.txtEmail.text = datos.email

            //Si se pulsa sobre el ImageView foto se navega la Fragment ZoomImagen
            binding.imageFoto.setOnClickListener {

                val bundle = Bundle()
                bundle.putString("UrlImagen", datos.foto)
                navController.navigate(R.id.irFragmentZoomImagenDetelles, bundle)
            }

        }

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        //Se instancia el objeto NavController
        navController = Navigation.findNavController(view)
    }


}