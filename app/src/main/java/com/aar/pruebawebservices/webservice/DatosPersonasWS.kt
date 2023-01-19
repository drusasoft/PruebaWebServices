//**************************************************************************************************
//                   Clase donde se mapea los datos obtenidos del WebService
//**************************************************************************************************
package com.aar.pruebawebservices.webservice

import com.aar.pruebawebservices.database.PersonaDB


data class DatosPersonasWS(
    val results:List<Results>
)

data class Results(
    val gender:String,
    val email:String,
    val id:Id,
    val name:Name,
    val location:Location,
    val picture:Picture
)

data class Id(
    val value:String
)

data class Name(
    val title:String,
    val first:String,
    val last:String
)

data class Location(
    val city:String,
    val state:String,
    val country:String
)

data class Picture(
    val large:String
)


//Funcion de extension que convierte los datos recibidos del WS en la estructura de datos que se va a usar en la BD Room
fun DatosPersonasWS.toDatosPersonasBD():List<PersonaDB>
{
    val listaPersonasDB = mutableListOf<PersonaDB>()

    //Se recorra la lista de Personas obtenidas del WS y se crea una lista de persaona con la Estructura de Datos usada por la BD Local
    results.forEach {

        if(it.id.value != null)
        {
            val personaDB = PersonaDB(it.id.value, it.name.first, it.name.last, it.gender, it.email, it.location.city, it.location.country, it.picture.large, false)
            listaPersonasDB.add(personaDB)
        }
    }

    return listaPersonasDB
}
