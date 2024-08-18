package com.example.proyecto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.modelo.Comentario
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import kotlin.random.Random

class ComentariosAdapter(private val context: Context, val comentariosPublicacion: ArrayList<Comentario>, publicacionActivity: PublicacionActivity)
    : RecyclerView.Adapter<ComentariosAdapter.SectionViewHolder>(){

    var likesAleatorios:Int = 0
    inner class SectionViewHolder(view: View):RecyclerView.ViewHolder(view){
        val usuario = view.findViewById<TextView>(R.id.tv_nombre_autor)
        val contenido = view.findViewById<TextView>(R.id.tv_contenido_comentario)
        val fecha = view.findViewById<TextView>(R.id.tv_fecha_comentario)
        val numLikes = view.findViewById<TextView>(R.id.tv_num_likes_comentarios)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.comentario,parent,false)
        return SectionViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return comentariosPublicacion.size
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val comentario = comentariosPublicacion[position]
        likesAleatorios = Random.nextInt(1,201)
        if (comentario!=null){
            holder.usuario.text = "User ${comentario.id_comentario}"
            holder.contenido.text = comentario.contenido
            holder.fecha.text = comentario.fecha
            holder.numLikes.text = likesAleatorios.toString()

        }
    }


}
