package com.aar.pruebawebservices.webservice

import com.aar.pruebawebservices.utils.DatosIP_2


data class DatosIPWS_2(

    var status: String?,
    var country:String?,
    var countryCode:String?,
    var region:String?,
    var regionName:String?,
    var city:String?,
    var zip:String?,
    var lat:Double?,
    var lon:Double?,
    var timezone:String?,
    var isp:String?,
    var org:String?,
    var asname:String?,
    var query:String?
)



//Funcion de extension para transformar los datos davueltos por el WS al formato adeacuado para mostrarlo en la IU
fun DatosIPWS_2.toDatosIP_2():DatosIP_2
{

    //Para aquellos campos del resultado del WS cuyo valor sea nulo se cambia para
    val statusConsulta = if(status == null) "fail" else status!!
    val pais = if(country == null) "--" else country!!
    val region = if(regionName == null) "--" else regionName!!
    val ciudad = if(city == null) "--" else city!!
    val cp = if(zip == null) "--" else zip!!
    val latitud = if(lat == null) "--" else lat.toString()
    val longitud = if(lon == null) "--" else lon.toString()
    val zonaHoraria = if(timezone == null) "--" else timezone!!
    val ispNombre = if(isp == null) "--" else isp!!
    val proveedor = if(asname == null) "--" else asname!!
    val ipConsultada = if(query == null) "--" else query!!

    return DatosIP_2(statusConsulta, pais, region, ciudad, cp, latitud, longitud, zonaHoraria, ispNombre, proveedor, ipConsultada)

}
