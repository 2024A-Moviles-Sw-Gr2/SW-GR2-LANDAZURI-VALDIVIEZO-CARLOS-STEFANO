package com.example.formula1cslv.entidades

class Fecha(
    private val anio: Int,
    private val mes: Int?,
    private val dia: Int,
) {
    init {
        this.anio
        this.mes
        this.dia
    }

    override fun toString(): String {
        return "$anio-$mes-$dia"
    }


}