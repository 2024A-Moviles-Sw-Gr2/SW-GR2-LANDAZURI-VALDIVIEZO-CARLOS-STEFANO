package com.example.proyecto

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.modelo.Publicacion
import org.w3c.dom.Text

class RecyclerViewAdapter(private val context: Context, private val listaPublicaciones: ArrayList<Publicacion>) : RecyclerView.Adapter<RecyclerViewAdapter.SectionViewHolder>() {

    inner class SectionViewHolder(view: View):RecyclerView.ViewHolder(view){
        val nombreForo: TextView = view.findViewById(R.id.tv_nombre_foro)
        val fechaPublicacionForo: TextView = view.findViewById(R.id.tv_fecha_publicacion)
        val nombrePublicacionForo: TextView = view.findViewById(R.id.tv_titulo_publicacion_foro)
        val contenidoPublicacionForo:TextView = view.findViewById(R.id.tv_contenido_publicacion_foro)
        val imagenForo: ImageView = view.findViewById(R.id.iv_foro)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}
