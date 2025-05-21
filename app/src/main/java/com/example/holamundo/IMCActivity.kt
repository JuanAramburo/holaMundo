package com.example.holamundo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class IMCActivity : AppCompatActivity() {

    private lateinit var txtPeso : TextView
    private lateinit var txtAltura : EditText
    private lateinit var btnCalcular : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar : Button
    private lateinit var lblResultado: TextView

    fun IniciarComponentes(){
        txtPeso = findViewById(R.id.txtPeso) as TextView
        txtAltura = findViewById(R.id.txtAltura) as EditText
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)
        lblResultado = findViewById(R.id.lblResultado)
    }

    fun eventosBotones(){
        btnCalcular.setOnClickListener {
            val pesoStr = txtPeso.text.toString()
            val alturaStr = txtAltura.text.toString()

            if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
                Toast.makeText(this, "Ingrese peso y altura", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val peso = pesoStr.toFloatOrNull()
            val altura = alturaStr.toFloatOrNull()

            if (peso == null || altura == null || altura <= 0f) {
                Toast.makeText(this, "Datos inválidos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val imc = peso / (altura * altura)
            val resultado = when {
                imc < 18.5 -> "Bajo peso"
                imc < 25 -> "Peso normal"
                imc < 30 -> "Sobrepeso"
                else -> "Obesidad"
            }

            lblResultado.text = "IMC: %.2f - %s".format(imc, resultado)
        }
        btnLimpiar.setOnClickListener(View.OnClickListener {
            txtPeso.setText("")
            txtAltura.setText("")
            lblResultado.setText("")
        })
        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("IMC")
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
        setContentView(R.layout.activity_imcactivity)

        IniciarComponentes()
        eventosBotones()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}