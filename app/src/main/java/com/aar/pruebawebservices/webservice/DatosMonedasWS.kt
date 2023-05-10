package com.aar.pruebawebservices.webservice

import android.util.Log
import kotlin.reflect.full.memberProperties

data class DatosMonedasWS(

    val base:String?,
    val last_updated:Int?,
    val exchange_rates: Exchange_Rates
)

data class Exchange_Rates(

    val ARS:Float?,
    val AUD:Float?,
    val BCH:Float?,
    val BGN:Float?,
    val BNB:Float?,
    val BRL:Float?,
    val BTC:Float?,
    val CAD:Float?,
    val CHF:Float?,
    val CNY:Float?,
    val CZK:Float?,
    val DKK:Float?,
    val DOGE:Float?,
    val DZD:Float?,
    val ETH:Float?,
    val EUR:Float?,
    val GBP:Float?,
    val HKD:Float?,
    val HRK:Float?,
    val HUF:Float?,
    val IDR:Float?,
    val ILS:Float?,
    val INR:Float?,
    val ISK:Float?,
    val JPY:Float?,
    val KRW:Float?,
    val LTC:Float?,
    val MAD:Float?,
    val MXN:Float?,
    val MYR:Float?,
    val NOK:Float?,
    val NZD:Float?,
    val PHP:Float?,
    val PLN:Float?,
    val RON:Float?,
    val RUB:Float?,
    val SEK:Float?,
    val SGD:Float?,
    val THB:Float?,
    val TRY:Float?,
    val TWD:Float?,
    val XRP:Float?,
    val ZAR:Float?
)


//Funcion de Extension usada para obtener el valor de cambio actual devuelto por el WS
fun DatosMonedasWS.toGetValorCambio():Float
{
    var valorCambio = 0.0f

    //Uso la reflexion de Kotlin (Libreria Externa definida en build.gradle) para recorrerme todos los campos del Dataclass Exchange_Rates
    //Y el campo que no tenga valor nulo es el valor de cambio devuelto por el WS en la consulta realizada.
    for(prop in Exchange_Rates::class.memberProperties)
    {
        if(prop.get(exchange_rates) != null)
            valorCambio = prop.get(exchange_rates).toString().toFloat()
    }

    return valorCambio
}
