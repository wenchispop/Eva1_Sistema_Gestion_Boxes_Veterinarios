# PetCare - Sistema de Gestión de Boxes Veterinarios

Aplicación de consola desarrollada en Kotlin para la Evaluación Parcial 1 de la asignatura DSY1105 - Desarrollo de Aplicaciones Móviles (Duoc UC).

## De qué trata el proyecto

PetCare SpA es una empresa que tiene clínicas veterinarias. Este sistema controla la atención de mascotas en una clínica con 10 boxes de atención.

El sistema permite:

- Registrar la entrada de un paciente y asignarle el primer box libre.
- Registrar la salida de un paciente, calcular cuánto debe pagar y emitir un ticket.
- Cobrar distinto según el tipo de paciente (Canino, Felino o Exótico) y el tipo de dueño (particular, convenio o municipal).
- Llevar la cuenta de lo recaudado en el turno, en total y por tipo de paciente.
- Responder consultas del negocio y mostrar un reporte al cerrar el turno.
- Seguir funcionando aunque ocurran errores, como un código inválido o un paciente que no existe.

Las entradas y salidas simulan la espera de un sensor usando corrutinas: la entrada tarda 3 segundos y la salida 6,5 segundos.

## Reglas de cobro

| Tipo de paciente | Tarifa por hora | Regla especial |
|---|---|---|
| Canino | $12.000 | Si el dueño tiene convenio, tiene 20% de descuento |
| Felino | $9.000 | Si la atención dura menos de 20 minutos, se cobra $0 |
| Exótico | $20.000 | Si es un animal silvestre, tiene un recargo del 30% |

El monto final se calcula en este orden:

1. Costo según el tiempo de atención y el tipo de paciente.
2. Se suma el IVA del 19%.
3. Si el dueño es municipal, se descuenta un 50% sobre el monto con IVA.

## Archivos del proyecto

| Archivo | Descripción |
|---|---|
| `Paciente` | Clase base (open class) con los datos comunes de todos los pacientes |
| `Canino` | Clase hija de Paciente con sus propias reglas de cobro |
| `Felino` | Clase hija de Paciente con sus propias reglas de cobro |
| `Exotico` | Clase hija de Paciente, incluye si el animal es silvestre |
| `TipoDueno` | Enum con los tres tipos de dueño |
| `Box` | Representa un box de atención con su número y estado |
| `Ticket` | Comprobante que se genera al finalizar la atención |
| `SistemaPetCare` | Lógica principal: entradas, salidas, cobros y recaudación |
| `Validaciones.kt` | Validaciones del código de atención, tipo de dueño y montos |
| `ErrorValidaciones.kt` | Excepciones propias del sistema |
| `Consultas.kt` | Consultas de negocio y reporte de cierre de turno |
| `Main.kt` | Punto de inicio del programa, carga los datos de prueba |

## Cómo ejecutar el proyecto

Requisitos:

- IntelliJ IDEA
- Kotlin 1.9 o superior
- Dependencia `kotlinx-coroutines-core` declarada en `build.gradle.kts`

Pasos:

1. Abrir IntelliJ IDEA.
2. Ir a **File > Open** y seleccionar la carpeta del proyecto.
3. Esperar a que Gradle termine de cargar las dependencias.
4. Abrir el archivo `Main.kt`.
5. Presionar el botón de ejecutar (Run) que aparece al lado de `fun main()`.
