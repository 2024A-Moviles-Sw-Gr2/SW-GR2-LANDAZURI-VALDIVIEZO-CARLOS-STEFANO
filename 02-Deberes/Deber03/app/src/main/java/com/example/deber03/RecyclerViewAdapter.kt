package com.example.deber03

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val context: Context,private val listaSeccionProductos: List<Pair<String, List<Int>>>) : RecyclerView.Adapter<RecyclerViewAdapter.SectionViewHolder>() {


    inner class SectionViewHolder(view: View):RecyclerView.ViewHolder(view){
        val sectionName:TextView = view.findViewById(R.id.tv_catalog_name)
        val description:TextView = view.findViewById(R.id.descripcion)
        val recyclerView:RecyclerView = view.findViewById(R.id.rv_product_catalog)
        val layoutHorizontal:ConstraintLayout = view.findViewById(R.id.rv_product_sections)
       
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_recycler_view_producto,parent,false)
        return SectionViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaSeccionProductos.size
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val (sectionName, imageList) = listaSeccionProductos[position]
        val sectionNameDes = sectionName.toString().split(",")
        holder.sectionName.text = sectionNameDes[0]
        holder.description.text = sectionNameDes[1]
        if (position==1){
            holder.layoutHorizontal.setBackgroundColor(getColor(context,R.color.fondo_rosa))
        }
        if(position==2){
            holder.layoutHorizontal.setBackgroundColor(getColor(context,R.color.white))
        }


        // Configurar el RecyclerView horizontal
        holder.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.recyclerView.adapter = HorizontalRecyclerViewAdapter(imageList)

    }

}
