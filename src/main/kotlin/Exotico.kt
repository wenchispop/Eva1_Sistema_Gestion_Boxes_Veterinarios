package org.example

//Clase para exotico, "hijo" de paciente
class Exotico (
    codigo: String,
    nombre: String,
    especie: String,
    tipoDueno: TipoDueno,
    val esSilvestre: Boolean     // propiedad extra que solo tienen los exoticos para calculo del costo total
) : Paciente(codigo, nombre, especie, tipoDueno, 20000.0) {

    //Exótico: tarifa base $20.000/hr. Existe una categoría adicional: animal silvestre. Si aplica. se añade un recargo del 30%. Este dato se registra al ingreso y no cambia. El detalle en pantalla debe indicar si es o no silvestre.

    // "override" = reemplaza el calculo del padre por uno propio
    override fun calcularCosto(minutos: Int): Double {
        val costo = super.calcularCosto(minutos)
        if (esSilvestre) {           // Silvestre = recargo del 30%
            return costo * 1.3
        }
        return costo
    }

    override fun mostrarDetalle(): String {
        val condicionSilvestre = if (esSilvestre) "Sí" else "No"
        return "${super.mostrarDetalle()} - Tipo: Exótico - Silvestre: $condicionSilvestre"
    }
}