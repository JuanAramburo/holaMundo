package com.example.holamundo

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class MonedaActivity : AppCompatActivity() {
    
    private lateinit var txtCantidad : EditText
    private lateinit var txtResultado : TextView
    private lateinit var btnCalcular : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar : Button
    private lateinit var spnMoneda : Spinner

    public fun iniciarComponentes(){
        txtCantidad = findViewById(R.id.txtCantidad)
        txtResultado = findViewById(R.id.txtResultado)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)
        spnMoneda = findViewById(R.id.spnMoneda)

        val items =  resources.getStringArray(R.array.monedas)

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,items)
        spnMoneda.adapter = adapter

    }

    public fun eventosClic(){
        var pos : Int = 0
        spnMoneda.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = parent?.getItemAtPosition(position).toString()
                pos = position
                Toast.makeText(applicationContext,"Selecciono " + item.toString(),Toast.LENGTH_SHORT).show()
                txtResultado.text = ""
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        btnCalcular.setOnClickListener(View.OnClickListener {
            val dolara = resources.getString(R.string.dolarA).toFloat()
            val dolarc = resources.getString(R.string.dolarC).toFloat()
            val euro = resources.getString(R.string.euro).toFloat()
            val libra = resources.getString(R.string.libra).toFloat()

            if(txtCantidad.text.toString().contentEquals("")){
                Toast.makeText(applicationContext, "Falto capturar la cantidad", Toast.LENGTH_SHORT).show()
            } else{
                val cantidad = txtCantidad.text.toString().toFloat()
                var resultado : Float = 0.0f
                resultado  = when (pos){
                    0 -> cantidad / dolara
                    1 -> cantidad /dolarc
                    2 -> cantidad / euro
                    3 -> cantidad / libra
                    else -> 0.0f
                }
                txtResultado.text = resultado.toString()
            }
        })
        btnLimpiar.setOnClickListener(View.OnClickListener {
            txtResultado.setText("")
            txtCantidad.text.clear()
        })
        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Moneda")
            builder.setMessage(" ¿Deseas regresar al menu?")

            builder.setPositiveButton("Aceptar"){dialog, which ->
                finish()
            }
            builder.setNegativeButton("Cancelar") { dialog, which ->
                Toast.makeText(
                    applicationContext,
                    "Quiza", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_moneda)

        iniciarComponentes()
        eventosClic()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}