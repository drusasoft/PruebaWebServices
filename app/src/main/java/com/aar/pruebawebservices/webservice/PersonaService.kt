package com.aar.pruebawebservices.webservice

import retrofit2.http.GET
import retrofit2.http.Query

interface PersonaService
{
    @GET("api/")
    suspend fun getPersonas(@Query("results") results:String):DatosPersonasWS
}