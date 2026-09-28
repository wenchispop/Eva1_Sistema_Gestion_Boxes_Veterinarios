package org.example

data class Ticket(
    val numero: Int,
    val paciente: Paciente,
    val minutos: Int,
    val monto: Double
)