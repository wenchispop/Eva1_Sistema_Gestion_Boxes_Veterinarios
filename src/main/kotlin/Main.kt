package org.example
import kotlinx.coroutines.runBlocking
import kotlin.collections.get

// ingresar un paciente. Si falla, muestra el error y el programa sigue
suspend fun ingresar(sistema: SistemaPetCare, paciente: Paciente) {
    try {
        sistema.registrarEntrada(paciente)
    } catch (e: Exception) {
        println("⚠ Error: ${e.message}")
    }
}

// registrar una salida. Si falla, muestra el error y el programa sigue
suspend fun sacar(sistema: SistemaPetCare, codigo: String, minutos: Int) {
    try {
        sistema.registrarSalida(codigo, minutos)
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}

// runBlocking permite usar funciones suspend dentro del main
fun main() = runBlocking {

    val sistema = SistemaPetCare()
    println("===== Sistema ${sistema.nombre} =====")

    println(
        """
        ⣿⣿⣿⠟⠛⠛⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⢋⣩⣉⢻
        ⣿⣿⣿⠀⣿⣶⣕⣈⠹⠿⠿⠿⠿⠟⠛⣛⢋⣰⠣⣿⣿⠀⣿
        ⣿⣿⣿⡀⣿⣿⣿⣧⢻⣿⣶⣷⣿⣿⣿⣿⣿⣿⠿⠶⡝⠀⣿
        ⣿⣿⣿⣷⠘⣿⣿⣿⢏⣿⣿⣋⣀⣈⣻⣿⣿⣷⣤⣤⣿⡐⢿
        ⣿⣿⣿⣿⣆⢩⣝⣫⣾⣿⣿⣿⣿⡟⠿⠿⠦⠀⠸⠿⣻⣿⡄⢻
        ⣿⣿⣿⣿⣿⡄⢻⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣾⣿⣿⣿⣿⠇⣼
        ⣿⣿⣿⣿⣿⣿⡄⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⣰
        ⣿⣿⣿⣿⣿⣿⠇⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢀⣿
        ⣿⣿⣿⣿⣿⠏⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢸⣿
        ⣿⣿⣿⣿⠟⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⣿
        ⣿⣿⣿⠋⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⣿
        ⣿⣿⠋⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⢸
        ⣿⠏⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡯⢸
        ⡏⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⢸
        """
    )
    println()

    // se deja un box malo para probar el estado FueraDeServicio
    sistema.boxes[9].estado = Box.EstadoBox.FueraDeServicio("Mantención")

    // ----- Entradas con los datos de prueba -----
    ingresar(sistema, Canino("CA46TO", "Lomito", "Gran Danés para los lados", TipoDueno.CONVENIO))
    ingresar(sistema, Canino("CA97LO", "Cholo", "Quiltro", TipoDueno.PARTICULAR))
    ingresar(sistema, Felino("FE66EM", "Salem", "Gato negro del diablooo", TipoDueno.PARTICULAR))
    ingresar(sistema, Exotico("EX99TR", "Lagartijo Iguala Lagarto", "Tiranosaurio Rex", TipoDueno.MUNICIPAL, true))
    ingresar(sistema, Exotico("EX71RG", "Rango", "Iguana verde", TipoDueno.PARTICULAR, false))

    sistema.mostrarBoxes()

    // ----- Salidas con los tiempos sugeridos -----
    sacar(sistema, "CA46TO", 75)
    sacar(sistema, "CA97LO", 180)
    sacar(sistema, "FE66EM", 666)
    sacar(sistema, "EX99TR", 18)
    sacar(sistema, "ZZ00ZZ", 30)   // paciente que no existe para que salte el error

    mostrarCierre(sistema)

    println("===== Gracias por usar ${sistema.nombre}! nos vemos!! =====")
}
