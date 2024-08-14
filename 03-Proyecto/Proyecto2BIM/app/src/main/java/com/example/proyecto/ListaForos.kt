package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListaForos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_foros)

        //Navegacion Crear foro
        val addForo = findViewById<ImageButton>(R.id.ib_add_foro)
        addForo.setOnClickListener {
            val intent  = Intent(this,CrearForo::class.java)
            startActivity(intent)
        }
        Database.tables = SQLiteHelper(this)
        val listaForos = Database.tables!!.getAllForos()
        val nombresForos = listaForos.map { it.nombre }

        val lvForos = findViewById<ListView>(R.id.lv_lista_foros)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombresForos)
        lvForos.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }
}