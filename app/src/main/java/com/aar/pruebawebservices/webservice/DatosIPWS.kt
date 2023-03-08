package com.aar.pruebawebservices.webservice

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatosIPWS(

    var ip_address:String,
    val city:String,
    val region:String,
    val postal_code:String,
    val country:String,
    val country_code:String,
    val longitude:Double,
    val latitude:Double,
    val flag: Flag

) : Parcelable

@Parcelize
data class Flag(

    val emoji:String,
    val png:String

) : Parcelable
