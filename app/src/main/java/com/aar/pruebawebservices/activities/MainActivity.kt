package com.aar.pruebawebservices.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.aar.pruebawebservices.R
import com.aar.pruebawebservices.databinding.LayoutActivityMainBinding





class MainActivity : AppCompatActivity()
{
    private lateinit var binding: LayoutActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)
        binding = LayoutActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.title = ""

        //Se Crea Objeto NavController
        val navController = Navigation.findNavController(this, R.id.NavHostFragment)

        //Añadimos el navController a la Toolbar(Actionbar), Para que se muestre la flecha volver y el titulo del frgament en la toolbar cuando se navega a otros fragments desde el fragment home
        binding.toolBar.setupWithNavController(navController)

        //Listener que se ejecuta cada vez que navegamos a un Fragment, lo usaoi para mostrar en la Toolbar el titulo correcto de cada Fragment
        navController.addOnDestinationChangedListener{ controller, destination, arguments ->

            when(destination.id)
            {
                controller.graph.startDestinationId->{ binding.toolBar.setTitle(R.string.app_name) }

                R.id.fragmentWSPersonas->{ binding.toolBar.setTitle(R.string.titFragmentWSPersonas) }
                R.id.fragmentZoomImagen->{ binding.toolBar.setTitle(R.string.titFragemntZoomImagen)}
                R.id.fragmentDetallesPersona->{ binding.toolBar.setTitle(R.string.titFragmentDetalles) }
                R.id.fragmentWSConsultarIP->{ binding.toolBar.setTitle(R.string.titFragmentConsultarIP)}
                R.id.fragmentDetallesIP->{ binding.toolBar.setTitle(R.string.titFragmentDetallesIP)}
                R.id.fragmentConsultarHoraMundial->{ binding.toolBar.setTitle(R.string.titFragmentConsultarHoraMundial) }
            }

        }

    }

}