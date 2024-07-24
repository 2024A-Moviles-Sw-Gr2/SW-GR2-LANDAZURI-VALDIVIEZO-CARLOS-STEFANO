package com.example.formula1cslv.entidades

class Carrera(
    private val identificadorGP: Int,
    private val nombreCircuito: String,
    private val fechaGP: String,
    private val longitudCarrera: Double,
) {
    init {
        this.identificadorGP
        this.nombreCircuito
        this.fechaGP
        this.longitudCarrera
    }
    fun getNombreCircuito():String{
        return nombreCircuito
    }
    fun getId():Int{
        return identificadorGP
    }



    override fun toString(): String {
        return "${nombreCircuito} | Fecha:${fechaGP}"
    }

}