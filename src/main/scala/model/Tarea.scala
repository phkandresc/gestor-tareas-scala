package model

import java.time.LocalDate

case class Tarea(
                id: Int,
                nombre: String,
                descripcion: String,
                prioridad: Prioridad,
                estado: Estado,
                fecha: LocalDate,
                etiquetas: Set[Etiqueta]
                )extends Serializable
