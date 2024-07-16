package com.example.formula1cslv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val arreglo = arrayListOf<String>()
    companion object {
        var tituloItem = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Arreglo provicional
        arreglo.add("Imola")
        arreglo.add("Monaco")


        //Logica lista
        val listView = findViewById<ListView>(R.id.lst_gp)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()// Para que se pueda actualizar

        registerForContextMenu(listView)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_main)) {
            v, insets->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left,systemBars.top,systemBars.right,systemBars.bottom)
            insets
        }

    }
    var posicionItemSeleccionado = -1
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menugp,menu)
        //ObtenerID
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        tituloItem = arreglo.get(posicionItemSeleccionado)
        return when (item.itemId){
            R.id.mi_editar ->{
                return true
            }
            R.id.mi_eliminar ->{
                return true
            }
            R.id.mi_verGP ->{
                irActividad(GrandPrix::class.java)
                return true
            }
            else-> super.onContextItemSelected(item)
        }
    }

    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this,clase)
        startActivity(intent)
    }

}