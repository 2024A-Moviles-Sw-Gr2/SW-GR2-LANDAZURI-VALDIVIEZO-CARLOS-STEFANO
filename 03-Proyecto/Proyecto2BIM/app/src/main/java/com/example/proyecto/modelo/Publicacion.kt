package com.example.proyecto.modelo

class Publicacion(
    val id_publicacion: Int,
    val id_foro: Int,
    val titulo: String,
    val contenido: String,
    val fecha: String,
    val likes: Int = 0
) {
    override fun toString(): String {
        return "Publicacion(id_publicacion=$id_publicacion, titulo='$titulo')"
    }
}
