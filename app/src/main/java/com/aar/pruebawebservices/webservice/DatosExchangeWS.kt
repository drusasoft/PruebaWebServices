package com.aar.pruebawebservices.webservice

data class DatosExchangeWS(

    val success:Boolean,

    val result:Double,

    val info:Info

)

data class Info(

    val timestamp:Int,

    val quote: Double
)
