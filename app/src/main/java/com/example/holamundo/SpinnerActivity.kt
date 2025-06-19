package com.example.holamundo

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SpinnerActivity : AppCompatActivity() {

    private lateinit var spn: Spinner
    private lateinit var btnCerrar: Button
    private lateinit var btnLimpiar : Button
    private lateinit var txtSel : TextView

    fun iniciarComponentes(){
        spn = findViewById(R.id.spnItems)
        btnCerrar = findViewById(R.id.btnCerrar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        txtSel = findViewById(R.id.txtSel)

        // agregar elementos
        val list = ArrayList<itemData>()

        list.add(itemData(getString(R.string.itemAlumnos),getString(R.string.itemAlumnos1),getString(R.string.mat0),R.drawable.alumnologo))
        list.add(itemData(getString(R.string.itemPersona1),getString(R.string.carrera1),getString(R.string.matPersona1),R.drawable.persona1))
        list.add(itemData(getString(R.string.itemPersona2),getString(R.string.carrera1),getString(R.string.matPersona2),R.drawable.persona2))
        list.add(itemData(getString(R.string.itemPersona3),getString(R.string.carrera1),getString(R.string.matPersona3),R.drawable.persona3))
        list.add(itemData(getString(R.string.itemPersona4),getString(R.string.carrera1),getString(R.string.matPersona4),R.drawable.persona4))

        //generar el adaptador
        val adapter = SpinnerAdapter(this,R.layout.spinner, R.id.lblCarrera,list)
        spn = findViewById(R.id.spnItems)
        spn.adapter = adapter

        spn.onItemSelectedListener = object
            : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = parent?.getItemAtPosition(position) as itemData
                txtSel.text = item.txtCarrera
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                txtSel.text = ""
            }
            }
        btnLimpiar.setOnClickListener(View.OnClickListener{
            spn.setSelection(0)
        })
        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Spinner")
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