package com.example.holamundo

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class SpinnerAdapter (
    private val context: Context,
    private val groupid: Int,
    id:Int,
    private val list: ArrayList<itemData>
): ArrayAdapter<itemData>(context,groupid,id,list){
    private val inflater:LayoutInflater=
        context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView?: inflater.inflate(groupid,parent,false)

        val imagen = itemView.findViewById<ImageView>(R.id.imgCategoria)
        imagen.setImageResource(list[position].imageId)

        val textCategoria = itemView.findViewById<TextView>(R.id.lblCategorias)
        textCategoria.text = list[position].txtCategoria

        val textDescripcion = itemView.findViewById<TextView>(R.id.lblDescripcion)
        textDescripcion.text = list[position].txtDescripcion
        return itemView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView?: inflater.inflate(groupid,parent,false)

        val imagen = itemView.findViewById<ImageView>(R.id.imgCategoria)
        imagen.setImageResource(list[position].imageId)

        val textCategoria = itemView.findViewById<TextView>(R.id.lblCategorias)
        textCategoria.text = list[position].txtCategoria

        val textDescripcion = itemView.findViewById<TextView>(R.id.lblDescripcion)
        textDescripcion.text = list[position].txtDescripcion

        return itemView
    }
}
