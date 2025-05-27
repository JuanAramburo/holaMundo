package com.example.holamundo

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.abs


class CotizacionActivity : AppCompatActivity() {

    private lateinit var txtCliente : TextView
    private lateinit var txtFolio : TextView
    private lateinit var txtDescripcion : EditText
    private lateinit var txtPrecio : EditText
    private lateinit var txtPorPagI : EditText
    private lateinit var rbd12 : RadioButton
    private lateinit var rbd24 : RadioButton
    private lateinit var rbd36 : RadioButton
    private lateinit var rbd48 : RadioButton
    private lateinit var txtPagoInicial : TextView
    private lateinit var txtTotalFin : TextView
    private lateinit var txtPagoMensual : TextView

    private lateinit var btnCalcular : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar : Button

    fun iniciarComponentes(){
        txtCliente = findViewById(R.id.txtCliente) as TextView
        txtFolio = findViewById(R.id.txtFolio) as TextView
        txtDescripcion = findViewById(R.id.txtDescripcion) as EditText
        txtPrecio = findViewById(R.id.txtPrecio) as EditText
        txtPorPagI = findViewById(R.id.txtPorcentaje) as EditText
        txtPagoInicial = findViewById(R.id.txtPagoInicial) as TextView
        txtPagoMensual = findViewById(R.id.txtPagoMensual) as TextView
        txtTotalFin = findViewById(R.id.txtTotalFin) as TextView

        rbd12 = findViewById(R.id.rbd12) as RadioButton
        rbd24 = findViewById(R.id.rbd24) as RadioButton
        rbd36 = findViewById(R.id.rbd36) as RadioButton
        rbd48 = findViewById(R.id.rbd48) as RadioButton

        btnCalcular = findViewById(R.id.btnCalcular) as Button
        btnLimpiar = findViewById(R.id.btnLimpiar) as Button
        btnCerrar = findViewById(R.id.btnCerrar) as Button

        var strCliente: String = intent.getStringExtra("Cliente").toString()
        txtCliente.text = strCliente.toString()

        var folio: Int = abs(Cotizacion().generaFolio())
        txtFolio.text = "Folio: " + folio.toString()

    }

    public fun eventosClic(){
        btnCalcular.setOnClickListener(View.OnClickListener {
            var cotizacion = Cotizacion()
            // validar
            if (txtDescripcion.text.toString().isEmpty() ||txtPrecio.text.toString().isEmpty() || txtPorPagI.text.toString().isEmpty()){
                Toast.makeText(this,"Falto capturar algun dato",Toast.LENGTH_SHORT).show()
            } else{
                txtFolio.text = cotizacion.generaFolio().toString()
                cotizacion.descripcion = txtDescripcion.text.toString()
                cotizacion.precio = txtPrecio.text.toString().toFloat()
                cotizacion.porPagInicial = txtPorPagI.text.toString().toFloat()

                if (rbd12.isChecked) cotizacion.plazos = 12
                if (rbd24.isChecked) cotizacion.plazos = 24
                if (rbd36.isChecked) cotizacion.plazos = 36
                if (rbd48.isChecked) cotizacion.plazos = 48

                txtPagoInicial.text = "Pago Inicial" + ": " + cotizacion.calcularPagoInicial().toString()
                txtTotalFin.text = "Total a Financiar" + ": " + cotizacion.calcularTotalFin().toString()
                txtPagoMensual.text = "Pago Mensual" + ": " + cotizacion.calcularPagoMensual().toString()
            }
        })

        btnLimpiar.setOnClickListener(View.OnClickListener {
            txtFolio.text = ""
            txtPagoInicial.text = "Pago Inicial"
            txtTotalFin.text = "Total a Financiar"
            txtPagoMensual.text = "Pago Mensual"

            txtDescripcion.setText("")
            txtPrecio.setText("")
            txtPorPagI.setText("")

            rbd12.isChecked = true

        })
        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Cotizacion")
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
        setContentView(R.layout.activity_cotizacion)

        iniciarComponentes()
        eventosClic()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}