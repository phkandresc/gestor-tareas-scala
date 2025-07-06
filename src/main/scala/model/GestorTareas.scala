package model

import java.io.{File, FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}
import scala.collection.immutable.HashMap
import scala.util.{Failure, Success, Try, Using}

object GestorTareas {
  private val RUTA = "tareas.dat"
  private var tareas = cargarTareas()
  private var idActual: Int = inicializarIdActual()

  def cargarTareas(): HashMap[Int, Tarea] = {
    val resultado = Using(new ObjectInputStream(new FileInputStream(RUTA))) {
        in => in.readObject().asInstanceOf[HashMap[Int, Tarea]]
      }

    resultado match {
      case Success(tareas) =>
        tareas
      case Failure(exception) =>
        HashMap[Int, Tarea]()
    }
  }

  def guardarTareas(tareas: HashMap[Int, Tarea]): Unit = {
    val resultado = Using(new ObjectOutputStream(new FileOutputStream(RUTA))) {
        out => out.writeObject(tareas)
    }

    resultado match {
      case Success(_) =>
        println(s"Tareas guardadas correctamente.")
      case Failure(exception) =>
        println(s"Error al guardar en archivo. ${exception.getMessage}")
    }
  }

  def agregarTarea(nuevaTarea: Tarea): Unit = {
    tareas = tareas + (idActual -> nuevaTarea)
    idActual = idActual + 1
    guardarTareas(tareas)
  }

  def inicializarIdActual(): Int = {
    if (tareas.isEmpty) 1
    else tareas.keys.max + 1
  }
}
