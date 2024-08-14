package com.example.proyecto

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.ContentValues
import android.widget.Button
import android.widget.EditText


class CrearForo : AppCompatActivity() {

    private lateinit var etNombreForo: EditText
    private lateinit var etDescripcion: EditText
    private lateinit var btnAgregarForo: Button
    private lateinit var dbHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_foro) // Asegúrate de que el nombre del layout sea correcto

        // Inicializa los elementos de la interfaz
        etNombreForo = findViewById(R.id.et_cf_nombre_foro)
        etDescripcion = findViewById(R.id.et_cf_descripcion)
        btnAgregarForo = findViewById(R.id.btn_agregar_foro)
        dbHelper = SQLiteHelper(this) // Asegúrate de que tu clase SQLiteHelper esté configurada correctamente

        // Configura el listener para el botón
        btnAgregarForo.setOnClickListener {
            agregarForo()
        }
    }

    private fun agregarForo() {
        val nombre = etNombreForo.text.toString()
        val descripcion = etDescripcion.text.toString()

        if (nombre.isNotBlank() && descripcion.isNotBlank()) {
            val values = ContentValues().apply {
                put("nombre", nombre)
                put("descripcion", descripcion)
            }

            val db = dbHelper.writableDatabase
            val newRowId = db.insert("Foro", null, values)

            if (newRowId != -1L) {
                // La inserción fue exitosa
                finish() // Cierra la actividad y vuelve a la actividad anterior
            } else {
                // Manejo del error
            }
        } else {
            // Manejo del caso en que los campos están vacíos
        }
    }
}
