package model

import java.time.LocalDate

case class Tarea(
                nombre: String,
                descripcion: String,
                prioridad: Prioridad,
                estado: Estado,
                fecha: LocalDate,
                etiquetas: Set[Etiqueta]
                )extends Serializable
