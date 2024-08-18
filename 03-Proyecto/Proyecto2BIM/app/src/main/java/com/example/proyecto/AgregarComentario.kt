package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date

class AgregarComentario : AppCompatActivity() {

    private lateinit var editTextComentario: EditText
    private lateinit var buttonGuardar: Button
    private lateinit var dbHelper: SQLiteHelper
    private var idPublicacion: Int = -1 // Valor predeterminado para manejar casos en los que no se pase el ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_comentario)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializa los componentes
        editTextComentario = findViewById(R.id.et_comentario_publicacion)
        buttonGuardar = findViewById(R.id.btn_guardar_comentario)
        dbHelper = SQLiteHelper(this)

        // Obtiene el ID de la publicación del Intent
        idPublicacion = intent.getIntExtra("id",-1)


        if (this.idPublicacion == -1) {
            Toast.makeText(this, "Error: ID de publicación no recibido", Toast.LENGTH_SHORT).show()
           // finish() // Termina la actividad si el ID no se pasó
        }

        // Configura el listener para el botón de guardar
        buttonGuardar.setOnClickListener {
            saveComentario()
        }

        // Configura el botón de regresar
        val backButton: ImageButton = findViewById(R.id.ib_regresar_a_publicacion)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveComentario() {
        val comentario = editTextComentario.text.toString()
        val fecha = obtenerFechaHoy() // Implementa esta función para obtener la fecha actual
        val likes = 0 // Inicializa con 0 o con el valor deseado

        if (comentario.isNotEmpty()) {
            val result = dbHelper.insertComentario(idPublicacion , comentario, fecha, likes)
            if (result != -1L) {
                Toast.makeText(this, "Comentario guardado con éxito", Toast.LENGTH_SHORT).show()
                editTextComentario.text.clear()
            } else {
                Toast.makeText(this, "Error al guardar el comentario", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "El comentario no puede estar vacío", Toast.LENGTH_SHORT).show()
        }
    }

    private fun obtenerFechaHoy(): String {
        val formato = SimpleDateFormat("dd/MM/yyyy")
        return formato.format(Date())
    }
}
