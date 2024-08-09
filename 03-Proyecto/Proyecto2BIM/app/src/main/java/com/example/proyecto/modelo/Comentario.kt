package com.example.proyecto.modelo

class Comentario(
    val id_comentario: Int,
    val id_publicacion: Int,
    val contenido: String,
    val fecha: String,
    val likes: Int = 0,
    val id_comentario_padre: Int? = null
) {

}
