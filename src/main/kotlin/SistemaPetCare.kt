package org.example

import kotlinx.coroutines.delay

class SistemaPetCare {
    val nombre = "PetCare"

    //Crea 10 boxes numerados, el maximo que indica la rubrica
    val boxes = MutableList(10) { i -> Box(i + 1) }

    //historial para guarda los tickets de los pacientes que ya salieron.
    val historial = mutableListOf<Ticket>()

    // total y los subtotales guardan la recaudación del turno.
    var totalRecaudado = 0.0
    var recaudadoCanino = 0.0
    var recaudadoFelino = 0.0
    var recaudadoExotico = 0.0

    //•	Se aplica el IVA del 19% sobre el resultado anterior.
    //•	Si el dueño tiene beneficio de municipal, se aplica un 50% de descuento sobre el monto con IVA.

    // calcula la tarifa, agrega el IVA y aplica el descuento municipal
    fun calcularMonto(paciente: Paciente, minutos: Int): Double {
        val tarifaConIva = paciente.calcularCosto(minutos) * 1.19
        val total = if (paciente.tipoDueno == TipoDueno.MUNICIPAL) {
            tarifaConIva * 0.5
        } else {
            tarifaConIva
        }
        validarMonto(paciente, minutos, total)
        return total
    }

    // registra la entrada y simula la espera
    suspend fun registrarEntrada(paciente: Paciente) {
        validarCodigo(paciente.codigo)

        // busca el primer box libre
        val box = boxes.firstOrNull { it.estaLibre() }
        if (box == null) {
            throw SistemaLlenoError("No hay boxes libres, el sistema está lleno")
        }

        box.estado = Box.EstadoBox.EnProceso("Registrando entrada")
        println("Registrando a ${paciente.nombre} en box ${box.numero}")
        delay(3000)

        box.estado = Box.EstadoBox.EnAtencion(paciente)
        println("Ingresó: ${paciente.mostrarDetalle()} -> Box ${box.numero}")
    }

    // registra la salida, calcula el cobro y libera el box
    suspend fun registrarSalida(codigo: String, minutos: Int) {
        // busca el box del paciente indicado.
        val box = boxes.firstOrNull {
            val est = it.estado
            est is Box.EstadoBox.EnAtencion && est.paciente.codigo == codigo
        }
        if (box == null) {
            throw PacienteNoEncontradoError("No hay ningún paciente con código $codigo")
        }

        // obtiene al paciente del estado del box
        val atencion = box.estado as? Box.EstadoBox.EnAtencion
            ?: throw PacienteNoEncontradoError("No hay ningún paciente con código $codigo")
        val paciente = atencion.paciente

        //calcula el monto antes de cambiar el estado del box
        val monto = calcularMonto(paciente, minutos)

        box.estado = Box.EstadoBox.EnProceso("Calculando tarifa")
        println("Loading... Procesando salida de ${paciente.nombre}...")
        delay(6500)

        // crea el ticket y lo agrega al historial
        val ticket = Ticket(historial.size + 1, paciente, minutos, monto)
        historial.add(ticket)

        // actualiza la recaudacion total y la correspondiente al tipo de paciente
        totalRecaudado += monto
        when (paciente) {
            is Canino -> recaudadoCanino += monto
            is Felino -> recaudadoFelino += monto
            is Exotico -> recaudadoExotico += monto
        }

        box.estado = Box.EstadoBox.Libre
        println("Ticket #${ticket.numero}: ${paciente.codigo} pagó $${monto}")
    }

    // muestra el estado actual de todos los boxes
    fun mostrarBoxes() {
        println("--- Estado de los boxes ---")
        boxes.forEach { println(it.descripcion()) }
    }
}
