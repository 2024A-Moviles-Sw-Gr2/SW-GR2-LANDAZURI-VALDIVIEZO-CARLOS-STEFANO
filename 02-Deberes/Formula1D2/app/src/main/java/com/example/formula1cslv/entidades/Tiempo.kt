package com.example.formula1cslv.entidades

class Tiempo(val horas:Int?,
             val minutos: Int?,
             val segundos: Double,) {
    init {
        this.horas
        this.minutos
        this.segundos
    }

    override fun toString(): String {
        return "${horas}:${minutos}:${segundos}"
    }
}