package com.example.formula1cslv

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.formula1cslv.entidades.Carrera

class MainActivity : AppCompatActivity() {
    private var GrandPrixs:ArrayList<Carrera> = arrayListOf();
    private var adaptador:ArrayAdapter<Carrera>? = null
    companion object {
        var tituloItem = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Arreglo provicional
        Database.tables = SqliteHelper(this)
        //Logica lista
        val listView = findViewById<ListView>(R.id.lst_gp)
        GrandPrixs = Database.tables!!.getAllGrandPrixs()
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            GrandPrixs
        )
        listView.adapter = adaptador
        adaptador!!.notifyDataSetChanged()// Para que se pueda actualizar

        //Boton crear

        val btnCrearCarrera = findViewById<Button>(R.id.btn_crearGP)
        btnCrearCarrera.setOnClickListener { irActividad(CrearGrandPrix::class.java) }

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
        tituloItem = GrandPrixs.get(posicionItemSeleccionado).getNombreCircuito()
        return when (item.itemId){
            R.id.mi_editar ->{
                irActividad(EditarGrandPrix::class.java,GrandPrixs[posicionItemSeleccionado].getId())
                GrandPrixs.clear()
                GrandPrixs.addAll(Database.tables!!.getAllGrandPrixs())
                adaptador!!.notifyDataSetChanged()
                return true
            }
            R.id.mi_eliminar ->{
                openDialog(GrandPrixs[posicionItemSeleccionado].getId())
                return true
            }
            R.id.mi_ver_gp ->{
                irActividad(GrandPrix::class.java,GrandPrixs[posicionItemSeleccionado])
                return true
            }
            else-> super.onContextItemSelected(item)
        }
    }

    private fun irActividad(clase:Class<*>, id: Int) {
        val intent = Intent(this,clase)
        if (id!=null){
            intent.apply {
                putExtra("idGpSelecionado",id)
            }
        }
        startActivity(intent)
    }

    private fun irActividad(clase:Class<*>, carrera: Carrera) {
        val intent = Intent(this,clase)
        if (carrera!=null){
            intent.apply {
                putExtra("carreraSeleccionada",carrera)
            }
        }
        startActivity(intent)
    }


    private fun openDialog(index: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar el Grand Prix?")
        builder.setPositiveButton(
            "Eliminar"
        ){_,_ ->
            Database.tables!!.deleteGrandPrix(index)
            GrandPrixs.clear()
            GrandPrixs.addAll(Database.tables!!.getAllGrandPrixs())
            adaptador!!.notifyDataSetChanged()
        }
        builder.setNegativeButton("Cancelar",null)
        builder.create().show()
    }

    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this,clase)
        startActivity(intent)
    }

}