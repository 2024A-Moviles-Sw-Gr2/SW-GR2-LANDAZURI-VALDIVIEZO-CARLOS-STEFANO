package com.example.formula1cslv

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CrearGrandPrix : AppCompatActivity() {
    private lateinit var fechaGp: EditText
    var calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_grand_prix)
        fechaGp = findViewById<EditText>(R.id.eTFechaGP)
        fechaGp.setOnClickListener{openDatePicker()}
    }
    private fun openDatePicker(){
        val dialog = DatePickerDialog.OnDateSetListener{ view,year,month,day ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,day)
            actualizarEditText()
        }
        DatePickerDialog(this,dialog,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()

    }
    fun actualizarEditText(){
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        fechaGp.setText(dateFormat.format(calendar.time))
    }

}