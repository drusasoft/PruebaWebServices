package com.aar.pruebawebservices.webservice

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatosHoraWS(

    val datetime:String

) : Parcelable
