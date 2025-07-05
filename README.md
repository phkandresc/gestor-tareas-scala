# Gestor de tareas en Scala con serializacion

## Modelos
- ### Enum
Un enum es una enumeración que define un conjunto de constantes. En este caso, se utiliza para:

`Estado`: Define los estados posibles de una tarea (PENDIENTE, EN_PROGRESO, COMPLETADA).

`Prioridad`: Define las prioridades posibles de una tarea (BAJA, MEDIA, ALTA).

- ### Case Class
Caracteristicas de una case class:

- Los campos son inmutables.
- Se genera automaticamente metodos toString, equals, hashCode, copy
- Pattern matching: se puede usar en expresiones match
- En este caso Tarea y Etiqueta implementan Serializable
- Serializable es una interfaz de java que permite que un objeto pueda ser convertido en una secuencia de bytes, para ser almacenado y posteriormente reconstruido.
- Se pueden comparar por valor.
