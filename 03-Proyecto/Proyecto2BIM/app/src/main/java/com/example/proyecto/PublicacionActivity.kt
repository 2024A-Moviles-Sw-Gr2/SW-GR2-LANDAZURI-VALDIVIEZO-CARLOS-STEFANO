package com.example.proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto.modelo.Comentario
import com.example.proyecto.modelo.Publicacion
import kotlin.random.Random

class PublicacionActivity : AppCompatActivity() {

    val db = SQLiteHelper(this)
    private lateinit var adapterComentarios: ComentariosAdapter;
    lateinit var comentariosPublicacion: ArrayList<Comentario>
    var likesAleatorios: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_publicacion)


        val idPublicacion = intent.getIntExtra("publicacionId",-1)
        Toast.makeText(this, "ID PUBLICACION RECIBIDA ${idPublicacion}", Toast.LENGTH_SHORT).show()
        if(idPublicacion!=-1){

            val publicacion = db.findPublicacionById(idPublicacion)
            Toast.makeText(this, "publicación  ${publicacion}", Toast.LENGTH_SHORT).show()

            comentariosPublicacion = db.getComentariosPorIdPublicacion(idPublicacion)
            Toast.makeText(this, "ID comentarios ${idPublicacion}", Toast.LENGTH_SHORT).show()
            val fechaPublicacion = findViewById<TextView>(R.id.tv_fecha_publicacion2)
            val nombrePublicacion = findViewById<TextView>(R.id.tv_titulo_publicacion)
            val contenidoPublicacion = findViewById<TextView>(R.id.tv_contenido_publicacion)
            val numLikes = findViewById<TextView>(R.id.tv_num_likes_publicacion)
            val comentaros = findViewById<TextView>(R.id.tv_num_comentarios_publicacion)
            val icono = findViewById<ImageView>(R.id.iv_foro_publicacion)
            icono.setImageResource(R.drawable.foro)
            // Colocar info la publicación

            likesAleatorios = Random.nextInt(1,101)
            nombrePublicacion.text = publicacion!!.titulo
            fechaPublicacion.text = publicacion!!.fecha
            contenidoPublicacion.text = publicacion.contenido
            numLikes.text = likesAleatorios.toString()
            comentaros.text = comentariosPublicacion!!.size.toString()+" Comentarios"
        }

        val regresar = findViewById<ImageButton>(R.id.ib_regresar_main)
        regresar.setOnClickListener {
            irActividad(MainActivity::class.java)
        }


        //Para añadir comentarios
        val aniadirComentario = findViewById<TextView>(R.id.tv_add_coment)
        aniadirComentario.setOnClickListener {
            val intent = Intent(this, AgregarComentario::class.java)
            intent.putExtra("id",idPublicacion)
            startActivity(intent)
        }


        inicializarComentarios()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
    private fun inicializarComentarios() {

        val recyclerView: RecyclerView = findViewById(R.id.rv_comentarios)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapterComentarios = ComentariosAdapter(this,comentariosPublicacion,this)
        recyclerView.adapter = adapterComentarios
    }
}