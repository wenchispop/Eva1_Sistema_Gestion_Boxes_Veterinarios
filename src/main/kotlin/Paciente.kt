package org.example
import java.time.LocalDateTime

// "open" permite que otras clases hereden de esta.
open class Paciente(
    val codigo: String,          // val = no cambia una vez registrado
    val nombre: String,
    val especie: String,
    val tipoDueno: TipoDueno,
    val tarifaBase: Double,      // Double porque es dinero
    val fechaIngreso: LocalDateTime = LocalDateTime.now()  // valor por defecto: la hora actual
) {
    // Cálculo general: horas × tarifa.
    // "open fun" permite que las hijas lo cambien (polimorfismo)
    open fun calcularCosto(minutos: Int): Double {
        return (minutos / 60.0) * tarifaBase   // 60.0 para no hacer división entera
    }

    // Texto para mostrar el paciente en pantalla
    open fun detalle(): String {
        return "$codigo - $nombre ($especie) - Dueño: $tipoDueno"
    }
}