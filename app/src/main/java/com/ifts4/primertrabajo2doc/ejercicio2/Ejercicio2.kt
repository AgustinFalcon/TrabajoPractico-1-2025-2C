package com.ifts4.primertrabajo2doc.ejercicio2

fun main() {

    try {

    } catch (e: Exception){}
}



sealed class Colors() {
    object Pepe
    class Pedro(val nombre:String)
    interface OnClick
}

enum class Colores(val exadecimal: Int) {
    RED(15),
}