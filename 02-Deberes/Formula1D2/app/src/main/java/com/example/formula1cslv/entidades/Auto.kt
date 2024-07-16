package com.example.formula1cslv.entidades

class Auto(private val numeroIdentificador:Int,
           private val escuderia:String,
           private val participa:Boolean,
           private val tiempoTotal: Tiempo,
           private val puntosObtendidos: Int,
           private val penalizacion:Double?,
           private val piloto:String,
    ) {

    init {
        this.numeroIdentificador
        this.escuderia
        this.participa
        this.tiempoTotal
        this.puntosObtendidos
        this.penalizacion
        this.piloto
    }
    override fun toString(): String {
        return "${piloto} | ${escuderia} #${numeroIdentificador}"
    }


}