package com.aar.pruebawebservices.webservice

import retrofit2.http.GET
import retrofit2.http.Query

interface ConsultarIPService
{
    //@GET("v1/")//******Para Prubas devuelvo todos los datos obtenidos del WS en formato String
    //suspend fun getDatosIP(@Query("api_key") api_key:String, @Query("ip_address") ip_address:String):String

    @GET("v1/")
    suspend fun getDatosIP(@Query("api_key") api_key:String, @Query("ip_address") ip_address:String):DatosIPWS
}