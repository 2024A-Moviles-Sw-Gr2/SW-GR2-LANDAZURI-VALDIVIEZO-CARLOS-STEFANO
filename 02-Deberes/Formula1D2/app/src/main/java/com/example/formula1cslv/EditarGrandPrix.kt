package com.example.formula1cslv

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EditarGrandPrix : AppCompatActivity() {

    private lateinit var fechaGp: EditText
    var calendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_grand_prix)
        fechaGp = findViewById<EditText>(R.id.editFechaGrandPrix)
        fechaGp.setOnClickListener { openDatePicker() }
        val idGpSelecionado = intent.getIntExtra("idGpSelecionado",-1)
        val btnActualizar = findViewById<Button>(R.id.btnActualizarGrandPrix)
        btnActualizar.setOnClickListener {
            if (idGpSelecionado != -1){
                Database.tables!!.updateGrandPrix(
                    idGpSelecionado,
                    fechaGp.text.toString())
            }else{
                Log.e("DestinationActivity", "idGpSelecionado no fue pasado en el Intent")
            }
            goToActivity(MainActivity::class.java)
        }

    }

    private fun openDatePicker() {
        val dialog = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            actualizarEditText()
        }
        DatePickerDialog(
            this,
            dialog,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()

    }

    fun actualizarEditText() {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        fechaGp.setText(dateFormat.format(calendar.time))
    }

    private fun goToActivity(
        activityClass: Class<*>
    ) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}