package com.example.deber03

import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarVista()
        configurarBottomNavigationView()
    }

    private fun configurarBottomNavigationView() {

    }

    private fun inicializarVista() {

        val listaSecciónProductos = listOf(
            "Packs de ofertas,Cualquier 3+ artículos | Desde $0.99>" to listOf(R.drawable.carcasa,R.drawable.soporte_celular,R.drawable.holster,R.drawable.funko),
            "SuperOfertas,Solo hoy.Termina: 09:18:56>" to listOf(
                R.drawable.figura_goku,R.drawable.dark_moon_sword,R.drawable.control_pc,R.drawable.nier_autoamta
            ),
            "BigSave,Grandes marcas|Garantía de calidad" to listOf(
                R.drawable.mouse,R.drawable.pins,R.drawable.pouch,R.drawable.zelda_esferas,R.drawable.twob_nier
            )
        )
        val recyclerView:RecyclerView = findViewById(R.id.rv_lista_productos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerViewAdapter(this,listaSecciónProductos)
    }
}