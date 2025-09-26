package com.ifts4.primertrabajo2doc.ejercicio2

import com.ifts4.primertrabajo2doc.ejercicio1.Auto

fun main() {
    val auto = Auto()
}



sealed class Colors() {
    object Pepe
    class Pedro(val nombre:String)
    interface OnClick
}

enum class Colores(val exadecimal: Int) {
    RED(15),
}
