package com.example.componentes

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener,
    SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {

    // Lista de valores - Spinner dinâmico
    private val mList = listOf("Gramas", "Kg", "Arroba", "Tonelada")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_toast.setOnClickListener(this)
        button_snack.setOnClickListener(this)
        button_get_spinner.setOnClickListener(this)
        button_set_spinner.setOnClickListener(this)

        spinner_dynamic.onItemSelectedListener = this

        seekbar.setOnSeekBarChangeListener(this)

        switch_on_off.setOnCheckedChangeListener(this)
        checkbox_on_off.setOnCheckedChangeListener(this)
        radio_yes.setOnCheckedChangeListener(this)
        radio_no.setOnCheckedChangeListener(this)

        // Carrega valores para spinner
        loadSpinner()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_toast -> {
                val toast = Toast.makeText(this, "TOAST", Toast.LENGTH_SHORT)

                // Posicionamento
                toast.setGravity(Gravity.BOTTOM, 0, 100)

                // Cor do texto - Layout original
                // val textView = toast.view.findViewById<TextView>(android.R.id.message)
                // textView.text = "Custom!"
                // textView.setTextColor(Color.RED)

                // Inflamos um layout criado especificamente para a toast
                val toastLayout = layoutInflater.inflate(R.layout.toast_layout, null)
                toast.view = toastLayout

                toast.show()
            }
            R.id.button_snack -> {
                val snackbar = Snackbar.make(linear_root, "Snack", Snackbar.LENGTH_LONG)

                // Mudando a cor do texto
                snackbar.setTextColor(Color.MAGENTA)

                // Ação dentro da snackbar
                snackbar.setAction("Desfazer", View.OnClickListener {
                    Snackbar.make(linear_root, "Desfeito!", Snackbar.LENGTH_SHORT).show()
                })

                // Customizar a cor do texto de ação
                snackbar.setActionTextColor(Color.BLUE)

                // Mudando a cor do plano de fundo
                snackbar.setBackgroundTint(Color.GREEN)

                snackbar.show()
            }
            R.id.button_get_spinner -> {
                // String
                val selectedItem = spinner_static.selectedItem
                // Valores
                val selectedItemId = spinner_static.selectedItemId
                val selectedItemPosition = spinner_static.selectedItemPosition
            }
            R.id.button_set_spinner -> {
                spinner_static.setSelection(1)
                spinner_dynamic.adapter = null
            }
            R.id.button_get_seekbar -> {
                toast(seekbar.progress.toString())
            }
            R.id.button_set_seekbar -> {
                seekbar.progress = 10
            }
        }
    }

    // Eventos Spinner
    override fun onNothingSelected(parent: AdapterView<*>?) {
        toast("NOTHING")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val value = parent?.getItemAtPosition(position).toString()
        toast(value)
    }

    // Eventos Seekbar
    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        text_seekbar_value.text = progress.toString()
    }

    /**
     * Trata evento de toque inicial no Seekbar - Quando o componente começa a ser arastado
     * */
    override fun onStartTrackingTouch(seekBar: SeekBar) {
        toast("Start seekbar")
    }

    /**
     * Trata evento de toque final no Seekbar
     * */
    override fun onStopTrackingTouch(seekBar: SeekBar) {
        toast("End seekbar")
    }

    // Switch E checkbox
    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        when (buttonView.id) {
            R.id.switch_on_off -> {
                toast("Switch: ${if (isChecked) "On" else "Off"}")

                // Obter checked
                // switch_on_off.isChecked

                // Atribuit checked
                // switch_on_off.isChecked = true / false
            }
            R.id.checkbox_on_off -> {
                toast("Checkbox: ${if (isChecked) "On" else "Off"}")

                // Obter checked
                // checkbox_on_off.isChecked

                // Atribuit checked
                // checkbox_on_off.isChecked = true / false
            }
            R.id.radio_yes -> {
                toast("Radio yes: ${if (isChecked) "On" else "Off"}")

                // Obter radio
                // radio_yes.isChecked

                // Atribuit radio
                // radio_yes.isChecked = true / false
            }
            R.id.radio_no -> {
                toast("Radio no: ${if (isChecked) "On" else "Off"}")
            }
        }
    }

    /**
     * Carrega valores dinâmicos spinner
     */
    private fun loadSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_dynamic.adapter = adapter
    }

    /**
     * Facilita uso da Toast notification
     */
    private fun toast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}
