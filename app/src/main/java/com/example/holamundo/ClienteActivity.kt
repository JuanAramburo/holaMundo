package com.example.holamundo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ClienteActivity : AppCompatActivity() {

    private lateinit var txtCliente : TextView
    private lateinit var btnIngresar : Button
    private lateinit var btnRegresar : Button

    public fun iniciarComponentes(){
        txtCliente = findViewById(R.id.txtCliente) as TextView
        btnRegresar = findViewById(R.id.btnRegresar) as Button
        btnIngresar = findViewById(R.id.btnEntrar) as Button
    }

    public fun eventosClic(){
        btnIngresar.setOnClickListener(View.OnClickListener {
            if (txtCliente.text.toString().isEmpty()){
                Toast.makeText(this,"Falto capturar el nombre del cliente",Toast.LENGTH_SHORT).show()
            } else{
                val intent = Intent(this,CotizacionActivity::class.java)
                intent.putExtra("Cliente",txtCliente.text.toString())
                startActivity(intent)
            }
        })
        btnRegresar.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_cliente)

        iniciarComponentes()
        eventosClic()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}