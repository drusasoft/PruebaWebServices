package com.aar.pruebawebservices.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aar.pruebawebservices.R
import com.aar.pruebawebservices.database.PersonaDB
import com.aar.pruebawebservices.databinding.LayoutRecyclerPersonasBinding
import com.squareup.picasso.Picasso





class PersonasAdapter(private val onClicListener: (PersonaDB, String)->Unit, private val onLongClickListener: (PersonaDB)->Boolean):ListAdapter<PersonaDB, PersonasAdapter.PersonasViewHolder>(PersonasCallback())
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonasViewHolder
    {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutRecyclerPersonasBinding.inflate(layoutInflater, parent, false)

        return PersonasViewHolder(binding, onClicListener, onLongClickListener)
    }

    override fun onBindViewHolder(holder: PersonasViewHolder, position: Int)
    {
        holder.onBind(getItem(position))
    }



    class PersonasViewHolder(private val binding: LayoutRecyclerPersonasBinding, private val onClicListener: (PersonaDB, String)->Unit, private val onLongClickListener: (PersonaDB)->Boolean):RecyclerView.ViewHolder(binding.root)
    {

        fun onBind(personaDB:PersonaDB)
        {

            binding.textViewRecyclerNombre.text = "${personaDB.nombre} ${personaDB.apellidos}"
            binding.textViewRecyclerPais.text = personaDB.pais

            Picasso.get().load(personaDB.foto)
                .error(R.drawable.ic_error)
                .into(binding.imageViewRecyclerFoto)

            //****** Se definen los clickListener sobre los elementos del Recycler y se ejecuta la Lambda definida para cada caso ******

            //Pulsacion sobre el ImageView que muestra la foto de la persona
            binding.imageViewRecyclerFoto.setOnClickListener { onClicListener(personaDB, "imageViewRecyclerFoto") }

            //Pulsaciones sobre el elemento completo del Recycler
            itemView.setOnClickListener { onClicListener(personaDB, "itemView") }
            itemView.setOnLongClickListener { onLongClickListener(personaDB) }

            //****************** Fin ClickListeners ******************

        }

    }

}



class PersonasCallback:DiffUtil.ItemCallback<PersonaDB>()
{
    override fun areItemsTheSame(oldItem: PersonaDB, newItem: PersonaDB): Boolean { return oldItem.id == newItem.id }

    override fun areContentsTheSame(oldItem: PersonaDB, newItem: PersonaDB): Boolean { return oldItem == newItem }
}