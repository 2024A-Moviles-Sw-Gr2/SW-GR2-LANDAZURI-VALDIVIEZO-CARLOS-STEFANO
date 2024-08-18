package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListaForos : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_foros)

        // Navegación Crear foro
        val addForo = findViewById<ImageButton>(R.id.ib_add_foro)
        addForo.setOnClickListener {
            val intent = Intent(this, CrearForo::class.java)
            startActivity(intent)
        }

        val regresar = findViewById<ImageButton>(R.id.ib_regresar_main)
        regresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Configuración del ListView
        val lvForos = findViewById<ListView>(R.id.lv_lista_foros)
        adapter = ArrayAdapter(this, R.layout.list_item_foro, R.id.textViewItemForo, mutableListOf())
        lvForos.adapter = adapter

        // Cargar datos iniciales
        loadForos()

        // Configuración de padding para la barra de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()
        // Recargar la lista de foros al regresar a la actividad
        loadForos()
    }

    private fun loadForos() {
        val listaForos = Database.tables!!.getAllForos()
        val nombresForos = listaForos.map { it.nombre }
        adapter.clear()
        adapter.addAll(nombresForos)
        adapter.notifyDataSetChanged()
    }
}
