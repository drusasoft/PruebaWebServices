package com.aar.pruebawebservices.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatosIP(

    var direccion_ip:String,
    val ciudad:String,
    val region:String,
    val cp:String,
    val pais:String,
    val cod_pais:String,
    val longitud:String,
    val latitud:String,
    val bandera:String?

) : Parcelable
