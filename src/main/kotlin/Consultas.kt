package org.example

// cuenta cuantos boxes estan libres
fun boxesDisponibles(sistema: SistemaPetCare): Int {
    return sistema.boxes.count { box -> box.estaLibre() }
}

// devuelve los tickets de pacientes con dueño de convenio
fun pacientesConvenio(sistema: SistemaPetCare): List<Ticket> {
    return sistema.historial.filter { ticket ->
        ticket.paciente.tipoDueno == TipoDueno.CONVENIO
    }
}

// calcula el ingreso promedio por paciente atendido
fun ingresoPromedio(sistema: SistemaPetCare): Double {
    if (sistema.historial.isEmpty()) {
        return 0.0
    }

    val totalIngresos = sistema.historial.sumOf { ticket -> ticket.monto }
    return totalIngresos / sistema.historial.size
}

// devuelve los coidgos de los pacientes que ya salieron
fun codigosFinalizados(sistema: SistemaPetCare): List<String> {
    return sistema.historial.map { ticket -> ticket.paciente.codigo }
}

//devuelve el ticket del paciente que estuvo mas tiempo
fun pacienteMasTiempo(sistema: SistemaPetCare): Ticket? {
    return sistema.historial.maxByOrNull { ticket -> ticket.minutos }
}

// identifica la especie de cada paciente para mostrarla en el cierre
private fun tipoPaciente(paciente: Paciente): String {
    return when (paciente) {
        is Canino -> "Canino"
        is Felino -> "Felino"
        is Exotico -> "Exótico"
        else -> "Otro"
    }
}

// La función devuelve el tipo de paciente con mayor recaudación
fun tipoConMasIngresos(sistema: SistemaPetCare): String {
    if (sistema.totalRecaudado == 0.0) {
        return "Ninguno"
    }
    val ingresosPorTipo = mapOf(
        "Canino" to sistema.recaudadoCanino,
        "Felino" to sistema.recaudadoFelino,
        "Exótico" to sistema.recaudadoExotico
    )
    return ingresosPorTipo.maxByOrNull { ingreso -> ingreso.value }?.key ?: "Ninguno"
}

//  muestra los tickets, las consultas del R4 y el resumen del turno
fun mostrarCierre(sistema: SistemaPetCare) {

    // ----- Respuestas para el R4 -----
    println("\n--- Respuestas ---")
    println("Boxes disponibles: ${boxesDisponibles(sistema)}")
    println("Pacientes con convenio: ${pacientesConvenio(sistema).map { it.paciente.nombre }}")
    println("Códigos finalizados: ${codigosFinalizados(sistema)}")
    println("Más tiempo: ${pacienteMasTiempo(sistema)?.paciente?.nombre}")
    println("\n------")
    println("\n===== CIERRE DE TURNO, PA LA CASAA =====")
    println("\n------")

    sistema.historial.forEach { ticket ->
        println(
            "Ticket #${ticket.numero} | ${tipoPaciente(ticket.paciente)} | " +
                "${ticket.paciente.codigo} | ${ticket.minutos} min | " +
                "$${"%.0f".format(ticket.monto)}"
        )
    }

    println("Total recaudado: $${sistema.totalRecaudado}")
    println("Pacientes atendidos: ${sistema.historial.size}")
    println("Ingreso promedio: $${ingresoPromedio(sistema)}")
    println("Tipo con más ingresos: ${tipoConMasIngresos(sistema)}")
    println("Boxes disponibles: ${boxesDisponibles(sistema)}")
}

//R4 de la rubrica
//El sistema debe mantener información actualizada sobre los boxes y los pacientes atendi-dos durante el turno, y debe ser capaz de responder a las siguientes consultas de negocio:
//•	¿Cuántos boxes están disponibles en este momento?
//•	¿Qué pacientes del historial del turno pertenecen a clientes convenio?
//•	¿Cuál es el ingreso promedio por paciente atendido en el turno?
//•	¿Cuáles son los códigos de todos los pacientes que han finalizado durante el turno?
//•	¿Qué paciente tuvo más tiempo de uso durante el turno?
//Reporte de cierre de turno:
//Al finalizar el turno, el sistema debe mostrar un resumen por cada paciente atendido: nú-mero de ticket, tipo, código de atención, tiempo de uso y monto pagado. Al final, debe mos-trar el total recaudado, la cantidad de pacientes atendidos, el ingreso promedio, el tipo de paciente que más ingresos generó y la cantidad de boxes disponibles al cierre.
