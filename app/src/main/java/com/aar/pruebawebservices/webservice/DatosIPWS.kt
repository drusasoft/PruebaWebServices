package com.aar.pruebawebservices.webservice

import com.aar.pruebawebservices.utils.DatosIP

data class DatosIPWS(

    var ip_address:String?,
    val city:String?,
    val region:String?,
    val postal_code:String?,
    val country:String?,
    val country_code:String?,
    val longitude:Double?,
    val latitude:Double?,
    val flag: Flag?

)

data class Flag(

    val emoji:String?,
    val png:String?

)


//Se convierten los datos de la IP devueltos por el WS en la estructura de datos usada por el FragmentDetallesIP para mostrarlos en la IU
//Para esto define una funcion de extension
fun DatosIPWS.toDatosIP():DatosIP
{
    //Como el WS puede devolver null en los campos del resultado, entonces sustituimos dichos nulos por el valor "--"
    // en los datos que vamos a mostrar al usuario
    val direccion_ip = if(ip_address == null) "--" else ip_address!!
    val ciudad = if(city == null) "--" else city
    val region = if(region == null) "--" else region
    val cp = if(postal_code == null) "--" else postal_code
    val pais = if(country == null) "--" else country
    val cod_pais = if(country_code == null) "--" else country_code
    val longitud = if(longitude == null) "--" else longitude.toString()
    val latitud = if(latitude == null) "--" else latitude.toString()
    val bandera = flag?.png

    return DatosIP(direccion_ip, ciudad, region, cp, pais, cod_pais, longitud, latitud, bandera)
}
