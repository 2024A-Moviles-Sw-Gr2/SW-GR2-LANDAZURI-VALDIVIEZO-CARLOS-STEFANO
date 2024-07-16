package com.example.formula1cslv.entidades

class Carrera(
    private val identificadorGP: Int,
    private var autos: Array<Auto>?,
    private val nombreCircuito: String,
    private val fechaGP: Fecha,
    private val longitudCarrera: Double,
) {
    init {
        this.identificadorGP
        this.autos
        this.nombreCircuito
        this.fechaGP
        this.longitudCarrera
    }

    override fun toString(): String {
        return "${nombreCircuito} | Fecha:${fechaGP}"
    }

}