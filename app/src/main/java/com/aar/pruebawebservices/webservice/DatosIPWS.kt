package com.aar.pruebawebservices.webservice

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatosIPWS(

    val ip_address:String,
    val city:String,
    val region:String,
    val postal_code:String,
    val country:String,
    val longitude:Double,
    val latitude:Double

) : Parcelable
