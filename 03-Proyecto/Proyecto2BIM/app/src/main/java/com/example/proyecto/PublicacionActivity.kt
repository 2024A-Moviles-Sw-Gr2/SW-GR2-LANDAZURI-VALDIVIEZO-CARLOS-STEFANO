package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Publicacion : AppCompatActivity() {

    val db = SQLiteHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_publicacion)


        val idPublicacion = intent.getStringExtra("publicacionId")


        //Para añadir comentarios
        val aniadirComentario = findViewById<TextView>(R.id.tv_add_coment)
        aniadirComentario.setOnClickListener {
            val nombrePublicacion =  findViewById<TextView>(R.id.et_titulo_publicacion)
            val intent = Intent(this, AgregarComentario::class.java)

            startActivity(intent)
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}