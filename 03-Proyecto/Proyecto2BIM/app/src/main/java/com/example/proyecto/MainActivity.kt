package com.example.proyecto

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.modelo.Publicacion

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Database.tables = SQLiteHelper(this)
        inicializarVista()
    }

    private fun inicializarVista() {

        val listaPublicaciones = arrayListOf(
            Publicacion(
                id_publicacion = 1,
                id_foro = 101,
                titulo = "Primer Post",
                contenido = "Este es el contenido de la primera publicación.",
                fecha = "2024-08-09",
                likes = 10
            ),
            Publicacion(
                id_publicacion = 2,
                id_foro = 101,
                titulo = "Segundo Post",
                contenido = "Este es el contenido de la segunda publicación.",
                fecha = "2024-08-10",
                likes = 5
            ),
            Publicacion(
                id_publicacion = 3,
                id_foro = 102,
                titulo = "Tercer Post",
                contenido = "Este es el contenido de la tercera publicación.",
                fecha = "2024-08-11",
                likes = 15
            )
            // Agrega más publicaciones según sea necesario
        )


        val recyclerView: RecyclerView = findViewById(R.id.rv_foros)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerViewAdapter(this,listaPublicaciones)
    }
}