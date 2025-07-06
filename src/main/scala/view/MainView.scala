package view

import scala.io.StdIn.readLine

object MainView {
  def mostrarMenuPrincipal(): String = {
    println("=== GESTOR DE TAREAS ===")
    println("1. Ver todas las tareas.")
    println("2. Agregar una nueva tarea.")
    println("5. Salir.")
    println("===========================")
    println("Seleccionar una opcion: ")
    val opc = readLine()
    opc
  }
  
}
