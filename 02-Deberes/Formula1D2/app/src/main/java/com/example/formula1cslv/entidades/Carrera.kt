package com.example.formula1cslv.entidades

import java.io.Serializable

class Carrera(
    private val identificadorGP: Int,
    private val nombreCircuito: String,
    private val fechaGP: String,
    private val longitudCarrera: Double,
    private val latitud: Double,
    private val longitud: Double,
):Serializable {
    init {
        this.identificadorGP
        this.nombreCircuito
        this.fechaGP
        this.longitudCarrera
        this.latitud
        this.longitud
    }
    fun getNombreCircuito():String{
        return nombreCircuito
    }
    fun getId():Int{
        return identificadorGP
    }
    fun getLatitud():Double{
        return latitud
    }
    fun getLongitud():Double{
        return longitud
    }

    override fun toString(): String {
        return "${nombreCircuito} | Fecha:${fechaGP}"
    }

}