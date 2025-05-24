package com.example.holamundo

import android.os.Bundle
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
import org.w3c.dom.Text

class ConversionActivity : AppCompatActivity() {

    private lateinit var txtCantidad : EditText
    private lateinit var txtResultado : TextView
    private lateinit var rbdCel : RadioButton
    private lateinit var rbdFar : RadioButton

    private lateinit var btnCalcular : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar : Button

    public fun iniciarComponentes(){

        txtCantidad = findViewById(R.id.txtCantidad)
        txtResultado = findViewById(R.id.txtResultado)
        rbdCel = findViewById(R.id.rbdCel)
        rbdFar = findViewById(R.id.rbdFar)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)

    }

    public fun eventosClic(){
        btnCalcular.setOnClickListener(View.OnClickListener {
            //Validacion
            if(txtCantidad.text.toString().isEmpty()){
                Toast.makeText(this,"Falto capturar cantidad",Toast.LENGTH_SHORT).show()
            } else{
                var cantidad:Float = txtCantidad.text.toString().toFloat()
                if (rbdCel.isChecked){
                    var celcius :Float = 0.0f
                    celcius = (cantidad * 9/5) + 32
                    txtResultado.text = celcius.toString()
                }
                if (rbdFar.isChecked){
                    var fahrenheit :Float = 0.0f
                    fahrenheit = (cantidad - 32) * 5/9
                    txtResultado.text = fahrenheit.toString()
                }
            }
            btnLimpiar.setOnClickListener(View.OnClickListener {
                txtCantidad.setText("")
                txtResultado.setText("")
                rbdCel.isChecked = false
                rbdFar.isChecked = false
            })
            btnCerrar.setOnClickListener(View.OnClickListener {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Conversion")
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

        })
    }



    /* public fun eventosBotones(){
        btnCalcular.setOnClickListener{
            val cantidad = txtCantidad.text.toString()

            if (cantidad.isEmpty()){
                Toast.makeText(this,"Ingresa una cantidad", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val grados = cantidad.toDouble()

            if (rbdCel.isChecked){
                val resCel = (grados * 9/5) + 32
                txtResultado.text = "$grados °c = %.2f °F".format(resCel)
            } else if (rbdFar.isChecked){
                val resFar = (grados - 32) * 5/9
                txtResultado.text = "$grados °F = %.2f °C".format(resFar)
            }else{
                Toast.makeText(this,"Selecciona una conversion", Toast.LENGTH_SHORT).show()
            }
        }
        btnLimpiar.setOnClickListener(View.OnClickListener {
            txtCantidad.setText("")
            txtResultado.setText("")
            rbdCel.isChecked = false
            rbdFar.isChecked = false
        })
        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Conversion")
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
    } */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_conversion)

        iniciarComponentes()
        eventosClic()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}