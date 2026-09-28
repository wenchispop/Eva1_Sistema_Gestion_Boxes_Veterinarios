package org.example

// Tipos de error. Heredan de Exception
class CodigoInvalidoError(mensaje: String) : Exception(mensaje)
class TarifaInvalidaError(mensaje: String) : Exception(mensaje)
class TipoDuenoInvalidoError(mensaje: String) : Exception(mensaje)
class PacienteNoEncontradoError(mensaje: String) : Exception(mensaje)
class SistemaLlenoError(mensaje: String) : Exception(mensaje)