package com.aar.pruebawebservices.webservice

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatosHoraWS(

    val datetime:String?,
    val timezone_name:String,
    val timezone_location:String,
    val gmt_offset:Int,
    val requested_location:String,
    val latitude:Double,
    val longitude:Double

) : Parcelable
