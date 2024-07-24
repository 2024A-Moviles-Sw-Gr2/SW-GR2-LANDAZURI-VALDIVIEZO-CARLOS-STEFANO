package com.example.formula1cslv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.formula1cslv.entidades.Auto

class GrandPrix : AppCompatActivity() {
    private var autos:ArrayList<Auto> = arrayListOf();
    private var adaptador:ArrayAdapter<Auto>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Intent
        val idGpSelecionado = intent.getIntExtra("idGpSelecionado",-1)
        setContentView(R.layout.activity_grand_prix)
        //Titulo
        val textView = findViewById<TextView>(R.id.txtVNombreGP)
        textView.setText(MainActivity.tituloItem)
        //Db
        Database.tables = SqliteHelper(this)
        autos = Database.tables!!.getCarsByGrandPrix(idGpSelecionado)

        //Logica lista
        val listView = findViewById<ListView>(R.id.lst_autos)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            autos
        )
        listView.adapter = adaptador
        adaptador!!.notifyDataSetChanged()// Para que se pueda actualizar

        registerForContextMenu(listView)
        //Button crear
        val crearAuto = findViewById<Button>(R.id.btn_crear_auto)
        crearAuto.setOnClickListener { irActividad(
            CrearAuto::class.java,
            idGpSelecionado
        )
            actualizarLista(idGpSelecionado)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_grandPrix)) {
                v, insets->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left,systemBars.top,systemBars.right,systemBars.bottom)
            insets
        }

    }

    private fun actualizarLista(idGpSelecionado: Int) {
        autos.clear()
        autos.addAll(Database.tables!!.getCarsByGrandPrix(idGpSelecionado))
        adaptador!!.notifyDataSetChanged()
    }

    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this,clase)
        startActivity(intent)
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
                irActividad(EditarAuto::class.java,autos[posicionItemSeleccionado])
                return true
            }
            R.id.mi_eliminar ->{
                openDialog(autos[posicionItemSeleccionado].getId())
                return true
            }
            else-> super.onContextItemSelected(item)
        }
    }

    private fun irActividad(clase: Class<EditarAuto>, autoSeleccionado: Auto) {
        val intentEditar = Intent(this,clase)
        if (autoSeleccionado!=null){
            intentEditar.apply {
                putExtra("idAuto",autoSeleccionado.getId())
                putExtra("idGpParticipa",intent.getIntExtra("idGpSelecionado",-1))
            }
        }
        startActivity(intentEditar)

    }

    private fun openDialog(index: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar el Participante?")
        builder.setPositiveButton(
            "Eliminar"
        ){_,_ ->
            Database.tables!!.deleteCar(index)
            autos.clear()
            autos.addAll(Database.tables!!.getCarsByGrandPrix(1))
            adaptador!!.notifyDataSetChanged()
        }
        builder.setNegativeButton("Cancelar",null)
        builder.create().show()
    }

    private fun irActividad(clase:Class<*>, idGp: Int) {
        val intent = Intent(this,clase)
        if (idGp!=null){
            intent.apply {
                putExtra("idGpParticipa",idGp)
            }
        }
        startActivity(intent)

    }
}