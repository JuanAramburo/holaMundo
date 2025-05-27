package com.example.holamundo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    private lateinit var crvHola : CardView
    private lateinit var crvImc : CardView
    private lateinit var crvGrados : CardView
    private lateinit var crvMoneda : CardView
    private lateinit var crvCotizacion : CardView
    private lateinit var crvSpinner : CardView
    private lateinit var crvSalir : CardView

    fun iniciarComponentes(){
        crvHola = findViewById(R.id.crvHola) as CardView
        crvImc = findViewById(R.id.crvImc) as CardView
        crvGrados = findViewById(R.id.crvConvertir) as CardView
        crvMoneda = findViewById(R.id.crvMonedas) as CardView
        crvCotizacion = findViewById(R.id.crvCotizacion) as CardView
        crvSpinner = findViewById(R.id.crvSpiner) as CardView
        crvSalir = findViewById(R.id.crvSalir) as CardView

    }

    fun eventosClic(){
        crvHola.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        })
        crvImc.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,IMCActivity::class.java)
            startActivity(intent)
        })
        crvGrados.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,ConversionActivity::class.java)
            startActivity(intent)
        })
        crvMoneda.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,MonedaActivity::class.java)
            startActivity(intent)
        })
        crvCotizacion.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,ClienteActivity::class.java)
            startActivity(intent)
        })
        crvSalir.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage(" ¿Deseas salir de la aplicacion ?")

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
        setContentView(R.layout.activity_menu2)

        iniciarComponentes()
        eventosClic()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}