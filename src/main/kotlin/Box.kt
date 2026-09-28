package org.example

// Un box tiene un número (no cambia) y un estado (sí cambia, por eso var)
class Box(val numero: Int, var estado: EstadoBox = EstadoBox.Libre) {

//•	Libre: el box está disponible. Puede recibir un nuevo paciente.
//•	EnAtencion: el box tiene un paciente asignado. El sistema debe saber qué pacien-te está en ese box.
//•	EnProceso: el box está siendo registrado. Ocurre durante las operaciones mien-tras el sistema espera al sensor. El sistema debe indicar el motivo del procesa-miento.
//•	FueraDeServicio: el box está inhabilitado. El sistema debe registrar el motivo.

    // sealed class = clase cerrada por estados, siempre son los mesmos
    sealed class EstadoBox {
        object Libre : EstadoBox()                                     // no necesita datos
        data class EnAtencion(val paciente: Paciente) : EstadoBox()    // guarda qué paciente está
        data class EnProceso(val motivo: String) : EstadoBox()         // guarda qué se está haciendo
        data class FueraDeServicio(val motivo: String) : EstadoBox()   // guarda por qué está malo
    }

    // Revisa si el box puede recibir un paciente.
    fun estaLibre(): Boolean {
        return when (estado) {
            is EstadoBox.Libre -> true
            is EstadoBox.EnAtencion -> false
            is EstadoBox.EnProceso -> false
            is EstadoBox.FueraDeServicio -> false
        }
    }

    // Texto para mostrar el box en pantalla
    fun descripcion(): String {
        // "val e =" para usar los datos de cada estado
        return when (val est = estado) {
            is EstadoBox.Libre -> "Box $numero: Libre"
            is EstadoBox.EnAtencion -> "Box $numero: En atención (${est.paciente.nombre})"
            is EstadoBox.EnProceso -> "Box $numero: En proceso (${est.motivo})"
            is EstadoBox.FueraDeServicio -> "Box $numero: Fuera de servicio (${est.motivo})"
        }
    }
}
