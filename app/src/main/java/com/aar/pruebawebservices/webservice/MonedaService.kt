package com.aar.pruebawebservices.webservice

import retrofit2.http.GET
import retrofit2.http.Query

interface MonedaService
{

    @GET("v1/live/")//******Para Pruebas devuelvo todos los datos obtenidos del WS en formato String
    suspend fun getConversion(@Query("api_key") api_key:String, @Query("base") base:String, @Query("target") target:String):String


}