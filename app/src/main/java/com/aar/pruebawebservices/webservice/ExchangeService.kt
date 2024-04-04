package com.aar.pruebawebservices.webservice

import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeService
{

    //@GET("convert")
    //suspend fun getExchange(@Query("access_key") access_key:String, @Query("from") from:String, @Query("to") to:String, @Query("amount") amount:String):String

    @GET("convert")
    suspend fun getExchange(@Query("access_key") access_key:String, @Query("from") from:String, @Query("to") to:String, @Query("amount") amount:String):DatosExchangeWS

}