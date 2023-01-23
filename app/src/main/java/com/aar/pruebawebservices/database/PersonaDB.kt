package com.aar.pruebawebservices.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Personas")
data class PersonaDB(

    @PrimaryKey
    val id:String,

    @ColumnInfo(name = "nombre")
    val nombre:String,

    @ColumnInfo(name = "apellidos")
    val apellidos:String,

    @ColumnInfo(name = "genero")
    val genero:String,

    @ColumnInfo(name = "email")
    val email:String,

    @ColumnInfo(name = "ciudad")
    val ciudad:String,

    @ColumnInfo(name = "pais")
    val pais:String,

    @ColumnInfo(name = "foto")
    val foto:String,

    @ColumnInfo(name = "seleccionada")
    val seleccionada:Boolean

):Parcelable
