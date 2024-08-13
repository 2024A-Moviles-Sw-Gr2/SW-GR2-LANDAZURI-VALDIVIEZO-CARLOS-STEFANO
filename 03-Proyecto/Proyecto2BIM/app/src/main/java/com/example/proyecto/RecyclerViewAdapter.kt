package com.example.proyecto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
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
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.foros,parent,false)
        return SectionViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaPublicaciones.size
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val publicacion = listaPublicaciones[position]
        holder.nombreForo.text = publicacion.id_foro.toString()
        holder.fechaPublicacionForo.text = publicacion.fecha.toString()
        holder.contenidoPublicacionForo.text = publicacion.contenido.toString()
        holder.nombrePublicacionForo.text = publicacion.titulo

        holder.itemView.findViewById<ImageButton>(R.id.ib_opciones_publicacion_foro).setOnClickListener { view ->
            val popupMenu = PopupMenu(context, view)
            val menuInflater = popupMenu.menuInflater
            menuInflater.inflate(R.menu.menu_popup, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_delete -> {
                        // Llama a la función para eliminar el foro
                        //Esto esta bien con BD
                        val dbHelper = SQLiteHelper(context)
                        val result = dbHelper.deleteForo(publicacion.id_foro)
                        if (result > 0) {
                            // Elimina la publicación de la lista y notifica el cambio
                            listaPublicaciones.removeAt(position)
                            notifyItemRemoved(position)
                        }
                        //TODO: la eliminacion de la lista de publicaciones que sea por ID FORO. Y se necesita la posicion actual.
                        listaPublicaciones.removeAt(position)
                        notifyItemRemoved(position)
                        true
                    }
                    else -> false
                }
            }

            popupMenu.show()
        }
    }






}
