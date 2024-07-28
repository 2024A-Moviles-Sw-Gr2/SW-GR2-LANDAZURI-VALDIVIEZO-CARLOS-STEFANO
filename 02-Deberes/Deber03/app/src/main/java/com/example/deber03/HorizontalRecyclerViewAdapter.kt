package com.example.deber03

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class HorizontalRecyclerViewAdapter(private val imageList: List<Int>) : RecyclerView.Adapter<HorizontalRecyclerViewAdapter.ImageViewHolder>() {
    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = view.findViewById(R.id.iv_product)
        val productPrice: TextView = view.findViewById(R.id.txtPrecioReducido)
        val productRealPrice: TextView = view.findViewById(R.id.txtPrecioReal)
        val estrellas:TextView = view.findViewById(R.id.estrellas)
        val starImg:ImageView = view.findViewById(R.id.estrellasimg)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_vista, parent, false)
        return ImageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.productImage.setImageResource(imageList[position])
        val precio = Random.nextDouble(0.0,50.0)
        holder.productPrice.text = String.format("$ %.1f",precio)
        holder.productRealPrice.text = String.format("$ %.1f",(precio*2))
        holder.productRealPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.estrellas.text = String.format("%.1f",Random.nextDouble(0.0,5.0))
        holder.starImg.setImageResource(R.drawable.star)
    }

    override fun getItemCount(): Int = imageList.size



}
