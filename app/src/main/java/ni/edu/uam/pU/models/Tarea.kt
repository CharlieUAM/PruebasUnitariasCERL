package ni.edu.uam.pU.models

data class Tarea(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    var estaCompletada: Boolean = false
)