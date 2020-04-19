package com.example.componentes

import android.app.DatePickerDialog
import android.opengl.Visibility
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_time.*
import java.text.SimpleDateFormat
import java.util.*

class TimeActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener,
    TimePicker.OnTimeChangedListener {

    private var mBrazilLocale = Locale("pt", "BR")
    private val mSimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", mBrazilLocale)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        button_date.setOnClickListener(this)
        button_get_time.setOnClickListener(this)
        button_set_time.setOnClickListener(this)

        timepicker.setOnTimeChangedListener(this)

        // Poderia ser útil mostrar a progressbar enquanto uma chamada assíncrona é feita
        // Uma vez que a resposta é recebida, é possível remover o elemento.
        // progressbar.visibility = View.GONE

        // Da mesma maneira, é possível torná-lo visível
        // progressbar.visibility = View.VISIBLE
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_date -> {

                // Obtém a instância do calendário
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                // Mostra o Datepicker utilizando os dados de hoje
                DatePickerDialog(this, this, year, month, day).show()
            }
            R.id.button_get_time -> {
                if (Build.VERSION.SDK_INT >= 23) {
                    val hour = timepicker.hour.toString()
                    val minute = timepicker.minute.toString()
                    toast("$hour : $minute")
                } else {
                    val hour = timepicker.currentHour
                    val minute = timepicker.currentMinute
                    toast("$hour : $minute")
                }
            }
            R.id.button_set_time -> {
                if (Build.VERSION.SDK_INT >= 23) {
                    timepicker.hour = 20
                    timepicker.minute = 15
                } else {
                    timepicker.currentHour = 20
                    timepicker.currentMinute = 15
                }
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        // Cria um calendário e atribui a data selecionada
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)

        // Converte a data selecionada para o formato imposto pelo SimpleDateFormat
        val date = mSimpleDateFormat.format(calendar.time)
        button_date.text = date
    }

    override fun onTimeChanged(view: TimePicker?, hourOfDay: Int, minute: Int) {
        toast("$hourOfDay:$minute")
    }

    /**
     * Facilita uso da Toast notification
     */
    private fun toast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}
