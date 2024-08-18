package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.interfaces.InterfaceOnClick
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(),InterfaceOnClick.ItemClickListener{

    private lateinit var adapaterPublicaiones: PublicacionesAdapter;

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
        val bottonNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        val nombreApp = findViewById<TextView>(R.id.textView4)
        nombreApp.setOnClickListener {
            val intent = Intent(this,PublicacionActivity::class.java)
            startActivity(intent)
        }


        bottonNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    // Código para mostrar la vista de inicio
                     true
                }
                R.id.community -> {
                    // Código para mostrar la vista de foros
                    val intent  = Intent(this,ListaForos::class.java)
                    startActivity(intent)
                     true
                }
                R.id.add -> {
                    val intent = Intent(this, CrearPublicacion::class.java)
                    startActivity(intent)
                     true
                }
                else -> false
            }
        }
        inicializarVista()

    }

    override fun onItemClick(position: Int) {
        val publicacionId = (adapaterPublicaiones).listaPublicaciones[position].id_publicacion
        irActividad(com.example.proyecto.PublicacionActivity::class.java, publicacionId)
        }
    private fun inicializarVista() {

        val listaPublicaciones = Database.tables!!.getAllPublicaciones()

        val recyclerView: RecyclerView = findViewById(R.id.rv_foros)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapaterPublicaiones = PublicacionesAdapter(this,listaPublicaciones,this)
        recyclerView.adapter = adapaterPublicaiones
    }
    private fun irActividad(clase: Class<*>, publicacionId: Int) {
        val intent = Intent(this, clase)
        intent.putExtra("publicacionId", publicacionId)
        startActivity(intent)
        }
}