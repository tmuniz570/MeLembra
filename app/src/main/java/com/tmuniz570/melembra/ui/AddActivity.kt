package com.tmuniz570.melembra.ui

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.tmuniz570.melembra.databinding.ActivityAddBinding
import com.tmuniz570.melembra.extensions.format
import com.tmuniz570.melembra.extensions.text
import com.tmuniz570.melembra.model.Lembrete
import com.tmuniz570.melembra.room.LembreteDao
import com.tmuniz570.melembra.room.LembreteDatabase
import java.util.*

class AddActivity : AppCompatActivity() {

    private lateinit var dao: LembreteDao
    private lateinit var binding : ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dao = LembreteDatabase.getInstance(this).lembreteDao()

        listeners()
    }

    private fun listeners() {
        binding.tilData.editText?.setOnClickListener{
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tilData.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER")
        }

        binding.tilHora.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()
            timePicker.addOnPositiveButtonClickListener {
                val hora = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour
                val minuto = if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                binding.tilHora.text = "$hora:$minuto"
            }
            timePicker.show(supportFragmentManager, null)
        }

        binding.btnCancelar.setOnClickListener {
            finish()
        }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.btnAdd.setOnClickListener {
            val lembrete = Lembrete(
                id = 0,
                lembrete = binding.tilLembrete.text,
                data = binding.tilData.text,
                hora = binding.tilHora.text,
                repetir = binding.cbRepete.isChecked
            )
            dao.insert(lembrete)

            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}