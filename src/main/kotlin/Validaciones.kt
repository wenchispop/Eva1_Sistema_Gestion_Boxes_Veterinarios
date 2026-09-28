package org.example

// ver que tenga 2 letras, 2 numeros y 2 letras
fun validarCodigo(codigo: String) {
    val formato = Regex("^[A-Z]{2}[0-9]{2}[A-Z]{2}$") //al buen regex nada le gana -> 2 letras, 2 numeros y 2 letras
    if (!formato.matches(codigo)) {
        throw CodigoInvalidoError("El código '$codigo' no es válido (ejemplo correcto: AB01CD)")
    }
}

// El monto no debe ser 0 o negativo, pero con los michis (felinos) por menos de 20 min si puede cobrar 0
fun validarMonto(paciente: Paciente, minutos: Int, monto: Double) {
    val michiGratis = paciente is Felino && minutos < 20
    if (monto <= 0 && !michiGratis) {
        throw TarifaInvalidaError("El monto calculado para ${paciente.codigo} no es válido: $monto")
    }
}

// Convierte un texto como "convenio" en TipoDueno.CONVENIO
// Si el texto no es uno de los 3, da un error
fun ValidarTipoDueno(texto: String): TipoDueno {
    return when (texto.lowercase()) {
        "particular" -> TipoDueno.PARTICULAR
        "convenio" -> TipoDueno.CONVENIO
        "municipal" -> TipoDueno.MUNICIPAL
        else -> throw TipoDuenoInvalidoError("El tipo de dueño '$texto' no es válido")
    }
}

