package com.example.formula1cslv.entidades

class Carrera(
    private val identificadorGP: Int,
    private val nombreCircuito: String,
    private val fechaGP: String,
    private val longitudCarrera: Double,
) {
    private lateinit var autos: Array<Auto>
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