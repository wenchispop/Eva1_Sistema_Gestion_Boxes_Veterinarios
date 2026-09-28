package org.example

//Clase para el canino, "hijo" de paciente
class Canino(
    codigo: String,
    nombre: String,
    especie: String,
    tipoDueno: TipoDueno
) : Paciente(codigo, nombre, especie, tipoDueno, 12000.0) {

    //Canino: tarifa base $12.000/hr. Si el dueño tiene convenio. se aplica un descuento del 20% al calcular el tiempo de atención.

    // "override" = reemplaza el calculo del padre por uno propio
    override fun calcularCosto(minutos: Int): Double {
        val costo = super.calcularCosto(minutos)
        return if (tipoDueno == TipoDueno.CONVENIO) costo * 0.8 else costo // Si el dueño tiene convenio, se descuenta un 20%, paga el 80%
    }

    override fun mostrarDetalle(): String {
        return "${super.mostrarDetalle()} - Tipo: Canino"
    }
}