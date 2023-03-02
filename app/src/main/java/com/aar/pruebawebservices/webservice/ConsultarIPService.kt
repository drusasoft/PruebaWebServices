package com.aar.pruebawebservices.webservice

import retrofit2.http.GET
import retrofit2.http.Query

interface ConsultarIPService
{
    @GET("v1/")
    suspend fun getDatosIP(@Query("api_key") api_key:String, @Query("ip_address") ip_address:String):String
}