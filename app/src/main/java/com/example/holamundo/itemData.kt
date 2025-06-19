package com.example.holamundo

data class itemData(
    var txtCarrera: String = "",
    var txtAlumno: String = "",
    var txtMatricula: String = "",
    var imageId: Int = 0
){
    constructor(item: itemData) : this(
        txtCarrera = item.txtCarrera,
        txtAlumno = item.txtAlumno,
        txtMatricula = item.txtMatricula,
        imageId = item.imageId
    )
}
