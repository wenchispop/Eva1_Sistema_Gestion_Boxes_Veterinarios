package org.example
import java.time.LocalDateTime

//•	Código de atención en formato válido del sistema (dos letras, dos dígitos, dos le-tras. Ejemplo: CA12CD). No cambia una vez registrado.
//•	Nombre y especie de la mascota. No cambia una vez registrado.
//•	Fecha y hora exacta de ingreso al sistema. No cambia una vez registrada.
//•	Tipo de dueño: particular, convenio o municipal. No cambia una vez registrado.

// "open" permite que otras clases hereden de esta.
open class Paciente(
    val codigo: String,          // val = no cambia una vez registrado var cuando cambia
    val nombre: String,
    val especie: String,
    val tipoDueno: TipoDueno,
    val tarifaBase: Double,      // Double porque es dinero
    val fechaIngreso: LocalDateTime = LocalDateTime.now()  // valor por defecto: la hora actual
) {

    // calculo general: horas × tarifa.
    // "open fun" permite que los hijos modifiquen el comportamiento de la funcion
    open fun calcularCosto(minutos: Int): Double {
        return (minutos / 60.0) * tarifaBase   // 60.0 para no hacer división entera
    }

    // Texto para mostrar el paciente en pantalla
    open fun mostrarDetalle(): String {
        return "$codigo - $nombre ($especie) - Dueño: $tipoDueno"
    }
}