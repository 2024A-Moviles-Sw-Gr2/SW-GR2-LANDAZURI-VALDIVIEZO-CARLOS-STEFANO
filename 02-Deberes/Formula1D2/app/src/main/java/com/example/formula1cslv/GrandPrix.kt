package com.example.formula1cslv

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
import com.example.formula1cslv.entidades.Auto
import com.example.formula1cslv.entidades.Tiempo
import java.io.CharArrayReader

class GrandPrix : AppCompatActivity() {
    val arreglo = arrayListOf<Auto>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grand_prix)
        //Titulo
        val textView = findViewById<TextView>(R.id.txtVNombreGP)
        textView.setText(MainActivity.tituloItem)
        //Arreglo provicional
        arreglo.add(Auto(1,"RedBull",true, Tiempo(1,2,3.15),25,1.5,"Max Verstappen"))
        arreglo.add(Auto(3,"Ferrari",true, Tiempo(1,2,3.90),18,0.0,"Carlos Sainz"))

        //logica lista
        //Logica lista
        val listView = findViewById<ListView>(R.id.lst_autos)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()// Para que se pueda actualizar

        registerForContextMenu(listView)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_grandPrix)) {
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
        inflater.inflate(R.menu.menuauto,menu)
        //ObtenerID
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar ->{
                return true
            }
            R.id.mi_eliminar ->{
                return true
            }
            else-> super.onContextItemSelected(item)
        }
    }
}