//**************************************************************************************************
//                   Clase donde se mapea los datos obtenidos del WebService
//**************************************************************************************************
package com.aar.pruebawebservices.webservice


data class DatosPersonasWS(
    val results:List<Results>
)

data class Results(
    val gender:String,
    val email:String,
    val name:Name,
    val location:Location,
    val picture:Picture
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

