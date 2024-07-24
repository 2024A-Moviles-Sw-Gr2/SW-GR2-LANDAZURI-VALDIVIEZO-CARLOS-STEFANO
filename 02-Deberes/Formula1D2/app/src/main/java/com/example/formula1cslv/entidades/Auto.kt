package com.example.formula1cslv.entidades

class Auto(private val numeroIdentificador:Int,
           private val escuderia:String,
           private val tiempoTotal: String,
           private val puntosObtendidos: Int,
           private val penalizacion:Double?,
           private val piloto:String,
    ) {

    init {
        this.numeroIdentificador
        this.escuderia
        this.tiempoTotal
        this.puntosObtendidos
        this.penalizacion
        this.piloto
    }
    fun getId():Int{
        return numeroIdentificador
    }
    override fun toString(): String {
        return "${piloto} | ${escuderia} #${numeroIdentificador} | ${tiempoTotal}"
    }


}