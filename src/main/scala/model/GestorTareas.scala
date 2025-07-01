package model

import java.io.{FileInputStream, ObjectInputStream}
import scala.util.{Try, Success, Failure}

object GestorTareas {
  private var tareas: List[Tarea] = List()

  def cargarTareas(ruta: String): List[Tarea] = {
    val resultado = Try {
      val archivo = new FileInputStream(ruta)
      val in = new ObjectInputStream(archivo)
      val tareas = in.readObject().asInstanceOf[List[Tarea]]
      in.close()
      tareas
    }

    resultado match {
      case Success(tareas) =>
        println(s"✅ Se cargaron ${tareas.length} tareas desde '$ruta'")
        tareas

      case Failure(exception) =>
        println(s"❌ Error al cargar tareas: ${exception.getMessage}")
        List.empty[Tarea] // Devuelve una lista vacía si ocurre un error
    }
  }

  def agregarTarea(t: Tarea): Unit = {

  }

  def eliminarTarea(id: Int): Unit = {

  }

  def buscarPorEtiqueta(etiqueta: String): List[Tarea] = {
    println("buscar por etiqueta")
    List.empty[Tarea]
  }

  def filtrarPorEstado(e: Estado): List[Tarea] = {
    println("filtrar por estado")
    List.empty[Tarea]
  }



}
