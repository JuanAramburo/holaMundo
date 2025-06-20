package com.example.holamundo

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SpinnerActivity : AppCompatActivity() {

    private lateinit var spn: ListView
    private lateinit var btnCerrar: Button
    private lateinit var btnLimpiar : Button
    private lateinit var txtSel : TextView

    fun iniciarComponentes(){
        spn = findViewById(R.id.lstItems)
        btnCerrar = findViewById(R.id.btnCerrar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        txtSel = findViewById(R.id.txtSel)

        // agregar elementos
        val list = ArrayList<itemData>()

        list.add(itemData(getString(R.string.itemPersona1),getString(R.string.carrera1),getString(R.string.matPersona1),R.drawable.persona1))
        list.add(itemData(getString(R.string.itemPersona2),getString(R.string.carrera1),getString(R.string.matPersona2),R.drawable.persona2))
        list.add(itemData(getString(R.string.itemPersona3),getString(R.string.carrera1),getString(R.string.matPersona3),R.drawable.persona3))
        list.add(itemData(getString(R.string.itemPersona4),getString(R.string.carrera1),getString(R.string.matPersona4),R.drawable.persona4))
        list.add(itemData(getString(R.string.itemPersona5),getString(R.string.carrera1),getString(R.string.matPersona5),R.drawable.persona5))
        list.add(itemData(getString(R.string.itemPersona6),getString(R.string.carrera1),getString(R.string.matPersona6),R.drawable.persona6))
        list.add(itemData(getString(R.string.itemPersona7),getString(R.string.carrera1),getString(R.string.matPersona7),R.drawable.persona7))
        list.add(itemData(getString(R.string.itemPersona8),getString(R.string.carrera1),getString(R.string.matPersona8),R.drawable.persona8))
        list.add(itemData(getString(R.string.itemPersona4),getString(R.string.carrera1),getString(R.string.matPersona4),R.drawable.persona4))
        list.add(itemData(getString(R.string.itemPersona4),getString(R.string.carrera1),getString(R.string.matPersona4),R.drawable.persona4))



        //generar el adaptador
        val adapter = SpinnerAdapter(this,R.layout.spinner, R.id.lblCarrera,list)
        spn = findViewById(R.id.lstItems)
        spn.adapter = adapter

        spn.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as itemData
            txtSel.text = item.txtCarrera
        }

        btnLimpiar.setOnClickListener(View.OnClickListener{
            spn.clearChoices()
            txtSel.text = "Se ha seleccionado"
            (spn.adapter as SpinnerAdapter).notifyDataSetChanged()
        })
        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Lista")
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
        setContentView(R.layout.activity_spinner)

        iniciarComponentes()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}