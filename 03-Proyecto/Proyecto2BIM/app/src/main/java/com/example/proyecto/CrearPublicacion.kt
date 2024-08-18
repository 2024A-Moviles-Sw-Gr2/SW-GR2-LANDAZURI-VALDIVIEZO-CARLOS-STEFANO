package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date

class CrearPublicacion : AppCompatActivity() {
    private var idForoSeleccionado:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_publicacion)
        Database.tables = SQLiteHelper(this)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val listaForos = Database.tables!!.getAllForos()
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listaForos.map { it.nombre }
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinner: Spinner = findViewById(R.id.spinnerForos)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val foroSeleccionado = listaForos[position]
                idForoSeleccionado = foroSeleccionado.id_foro
                // Aquí puedes usar el ID del foro seleccionado
                Toast.makeText(
                    this@CrearPublicacion,
                    "ID del foro seleccionado: $idForoSeleccionado",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                idForoSeleccionado = null
            }
        }


        val backButton: ImageButton = findViewById(R.id.ib_regresar_main)
                backButton.setOnClickListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

        val btnPublicar: Button = findViewById(R.id.btn_publicar)
        btnPublicar.setOnClickListener {
            Database.tables = SQLiteHelper(this)
            val tituloPublicacion = findViewById<EditText>(R.id.et_titulo_publicacion)
            val contenidoPublicacion = findViewById<EditText>(R.id.et_contenido_publicacion)
            val fecha = obtenerFechaHoy()
            idForoSeleccionado?.let { id->
                Database.tables!!.insertPublicacion(
                    id,
                    tituloPublicacion.text.toString(),
                    contenidoPublicacion.text.toString(),
                    fecha,
                    0
                )
            }?:run{
                Toast.makeText(this, "Por favor, selecciona un foro primero", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun obtenerFechaHoy(): String {
        val formato = SimpleDateFormat("dd/MM/yyyy")
        return formato.format(Date())
    }


}