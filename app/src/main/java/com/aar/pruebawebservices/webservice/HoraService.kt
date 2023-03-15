package com.aar.pruebawebservices.webservice

import retrofit2.http.GET
import retrofit2.http.Query

interface HoraService
{

    //@GET("v1/current_time/")//******Para Prubas devuelvo todos los datos obtenidos del WS en formato String
    //suspend fun getHora(@Query("api_key") api_key:String, @Query("location") location:String):String

    @GET("v1/current_time/")
    suspend fun getHora(@Query("api_key") api_key:String, @Query("location") location:String):DatosHoraWS

}