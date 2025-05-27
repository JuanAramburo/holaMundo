package com.example.holamundo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.Serializable
import kotlin.random.Random

class Cotizacion : Serializable {
    var numCotizacion: Int = 0
    var descripcion: String = ""
    var porPagInicial: Float = 0.0f
    var precio: Float = 0.0f
    var plazos: Int = 0

    constructor(){
        this.numCotizacion = 0
        this.descripcion = ""
        this.porPagInicial = 0.0f
        this.precio = 0.0f
        this.plazos = 0
    }

    public fun calcularPagoInicial(): Float{
        return this.porPagInicial
    }
    public fun calcularTotalFin(): Float{
        return this.precio - this.calcularPagoInicial()
    }
    public fun calcularPagoMensual(): Float{
        return this.calcularTotalFin() / this.plazos
    }
    public fun generaFolio(): Int{
        return Random.nextInt(0, 333)
    }

}