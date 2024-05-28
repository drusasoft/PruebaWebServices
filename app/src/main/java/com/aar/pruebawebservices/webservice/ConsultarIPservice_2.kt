package com.aar.pruebawebservices.webservice

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ConsultarIPservice_2
{

    @GET("json/{query}")
    suspend fun getDatosIP(@Path("query") query:String, @Query("fields") fields:String): DatosIPWS_2

}