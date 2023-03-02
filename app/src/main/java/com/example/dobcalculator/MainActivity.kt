package com.example.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var tvAgeinMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeinMinutes = findViewById(R.id.tvAgeinMinutes)
        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {
              clickDatePicker()
        }

    }
    private fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
        { view, year, month, dayOfMonth ->
            Toast.makeText(this,
                "Year was $year, month was $month, day was $dayOfMonth", Toast.LENGTH_LONG).show()

            val selectedDate = "$dayOfMonth/${month+1}/$year"
            tvSelectedDate?.text = selectedDate
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            theDate?.let {
                val selectedDateinMinutes = theDate.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                currentDate?.let {
                    val currentDateinMinutes = currentDate.time / 60000
                    val differenceinMinutes = currentDateinMinutes - selectedDateinMinutes
                    tvAgeinMinutes?.text = differenceinMinutes.toString()
                }


            }

        }, year,
            month, day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
            dpd.show()

    }
}