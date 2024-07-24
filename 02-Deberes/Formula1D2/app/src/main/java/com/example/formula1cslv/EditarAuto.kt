package com.example.formula1cslv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EditarAuto : AppCompatActivity() {
    private lateinit var horaFinalizacion: EditText
    private var calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_auto)
        horaFinalizacion = findViewById(R.id.editTiempoFin)
        horaFinalizacion.setOnClickListener { openTimePicker() }
        val idAuto = intent.getIntExtra("idAuto",-1)
        val puntos = findViewById<EditText>(R.id.editPuntosAuto)
        val btnActualizar = findViewById<Button>(R.id.btnActualizarAuto)
        btnActualizar.setOnClickListener {
            if (idAuto != -1){
                Database.tables!!.updateCar(
                    idAuto,
                    horaFinalizacion.text.toString(),
                    puntos.text.toString().toInt())
            }else{
                Log.e("DestinationActivity", "idGpSelecionado no fue pasado en el Intent")
            }
            goToActivity(GrandPrix::class.java)
        }


    }
    private fun openTimePicker() {
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.dialog_time_picker, null)

        val hourPicker: NumberPicker = dialogView.findViewById(R.id.hourPicker)
        val minutePicker: NumberPicker = dialogView.findViewById(R.id.minutePicker)
        val secondPicker: NumberPicker = dialogView.findViewById(R.id.secondPicker)
        val millisecondPicker: NumberPicker = dialogView.findViewById(R.id.millisecondPicker)

        hourPicker.minValue = 0
        hourPicker.maxValue = 23
        hourPicker.wrapSelectorWheel = true

        minutePicker.minValue = 0
        minutePicker.maxValue = 59
        minutePicker.wrapSelectorWheel = true

        secondPicker.minValue = 0
        secondPicker.maxValue = 59
        secondPicker.wrapSelectorWheel = true

        millisecondPicker.minValue = 0
        millisecondPicker.maxValue = 999
        millisecondPicker.wrapSelectorWheel = true

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("OK") { _, _ ->
                val hour = hourPicker.value
                val minute = minutePicker.value
                val second = secondPicker.value
                val millisecond = millisecondPicker.value

                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                calendar.set(Calendar.SECOND, second)
                calendar.set(Calendar.MILLISECOND, millisecond)
                updateTimeEditText()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    private fun updateTimeEditText() {
        val timeFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.US)
        horaFinalizacion.setText(timeFormat.format(calendar.time))
    }
    private fun goToActivity(
        activityClass: Class<*>
    ) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}