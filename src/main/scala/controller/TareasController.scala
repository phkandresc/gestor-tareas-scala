package controller

import model.{Estado, Etiqueta, GestorTareas, Prioridad, Tarea}
import view.MainView

import java.time.LocalDate
import scala.io.StdIn.readLine
import scala.util.{Failure, Success, Try}

object TareasController {

  def iniciar(): Unit = {
    var flag = true

    while(flag) {
      val opcion = MainView.mostrarMenuPrincipal()

      opcion match {
        case "1" =>
          mostrarTareas()
        case "2" =>
          solicitarDatosTarea()
        case "5" =>
          flag = false
        case _ =>
          println("Opcion no valida. Intentelo nuevamente")
      }
    }
  }

  def solicitarDatosTarea(): Unit = {
    println("=== Nueva Tarea ===")
    println("Nombre:")
    var nombre = solicitarTexto()
    println("Descripcion:")
    var descripcion = readLine()
    println("Prioridad:")
    var prioridad = seleccionarPrioridad()
    println("Fecha de vencimiento:")
    val fecha = solicitarFecha()
    println("Etiquetas: ")
    val etiquetas = agregarEtiqueta()

    val nuevaTarea = Tarea(nombre, descripcion, prioridad, Estado.Pendiente, fecha, etiquetas)
    crearTarea(nuevaTarea)
  }

  def crearTarea(tarea: Tarea): Unit = {
    GestorTareas.agregarTarea(tarea)
  }

  def solicitarTexto(): String = {
    val input = readLine()

    if (input == null || input.trim.isEmpty) {
      println("❌ El campo no puede estar vacío. Intente nuevamente:")
      solicitarTexto()
    } else if (input.trim.length > 100) {
      println("❌ El texto no puede exceder 100 caracteres. Intente nuevamente:")
      solicitarTexto()
    } else {
      input.trim
    }
  }
  
  def agregarEtiqueta(): Set[Etiqueta] ={
    var etiquetas = Set.empty[Etiqueta]
    var continuar = true

    while (continuar) {
      println("Ingresa una etiqueta (o 'fin' para terminar):")
      val input = readLine().trim

      if (input.toLowerCase == "fin" || input.isEmpty) {
        continuar = false
      } else {
        etiquetas = etiquetas + Etiqueta(input)
        println(s"Etiquetas actuales: ${etiquetas.mkString("| ")}")
      }
    }

    etiquetas
  }
  
  def solicitarFecha(): LocalDate = {
    println("Ingresa la fecha (YYYY-MM-DD): ")
    val fechaTexto = readLine()

    Try {
      LocalDate.parse(fechaTexto)
    } match {
      case Success(fecha) => fecha
      case Failure(exception) =>
        println(s"Fecha inválida. Use formato YYYY-MM-DD (ejemplo: 2024-01-15)")
        solicitarFecha()
    }
  }
  
  def seleccionarPrioridad(): Prioridad = {
    mostrarOpcionesPrioridad()
    println("Ingresa el numero: ")
    val opcion = readLine()

    Try {
      val indice = opcion.toInt - 1
      Prioridad.values(indice)
    } match {
      case Success(prioridad) => prioridad
      case Failure(exception) =>
        println(s"Opción inválida. Intentalo de nuevo. ${exception}")
        seleccionarPrioridad() 
    }
  }
  
  def mostrarOpcionesPrioridad(): Unit = {
    Prioridad.values.zipWithIndex.foreach((p, index) => println(s"- ${index+1} ${p}"))
  }

  def mostrarOpcionesEstado(): Unit = {
    Estado.values.zipWithIndex.foreach((e, index) => println(s"- ${index+1}. ${e}"))
  }

  def mostrarTareas(): Unit = {
    val tareas = GestorTareas.cargarTareas()
    println(s"ID NOMBRE DESCRIPCION PRIORIDAD ESTADO FECHA ETIQUETAS")
    tareas.foreach { case (id, tarea) =>
      println(f"${id}%3d ${tarea.nombre} ${tarea.descripcion} ${tarea.prioridad} ${tarea.estado} ${tarea.fecha} ${tarea.etiquetas.mkString("| ")}")
      println("-------------------------------------------------")
    }
  }
}
