package org.example

//Clase para felino, "hijo" de paciente
class Felino (
    codigo: String,
    nombre: String,
    especie: String,
    tipoDueno: TipoDueno
) : Paciente(codigo, nombre, especie, tipoDueno, 9000.0) {

    //Felino: tarifa base $9.000/hr. Si el tiempo de atención es inferior a 20 minutos. el cobro es $0 independientemente del tipo de dueño.

    // "override" = reemplaza el calculo del padre por uno propio
    override fun calcularCosto(minutos: Int): Double {
        val costo = super.calcularCosto(minutos)
        return if (minutos < 20) costo * 0 else costo
    }

    override fun mostrarDetalle(): String {
        return "${super.mostrarDetalle()} - Tipo: Felino"
    }
}