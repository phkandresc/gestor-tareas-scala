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

### Try
- Paquete de scala.util
- Es una abstraccion que permite manejar errores.
- Pattern matching
- Si la operacion tiene exito, retorna Success(valor)
- Si ocurre una excepcion, retorna Failure(exception)

### Using
- Funcion que permite gestionar recursos automaticamente, cerrandolos al final del bloque incluso si existe una excepcion.
- Retorna un Try[T]
- Using(recurso) { bloque }

#### Leer un archivo serializado .dat
- Using para manejo de recursos
- ObjectInputStream para leer objetos completos desde un archivo binario.(No lee archivos directamente)
- Se construye ObjectInputStream con un FileInputStream
- FileInputStream permite abrir el archivo como un flujo de bytes.
- El metodo readObject() permite leer el objeto.
- readObject() devuelve un Any.
- Se debe hacer cast del objeto leido.

