package ni.edu.uam.pU.models

class GestorTareas {
    private val tareas = mutableListOf<Tarea>()

    fun agregarTarea(tarea: Tarea) {
        tareas.add(tarea)
    }

    fun eliminarTarea(id: Int) {
        tareas.removeIf { it.id == id }
    }

    fun marcarComoCompletada(id: Int) {
        tareas.find { it.id == id }?.estaCompletada = true
    }

    fun obtenerTareasPendientes(): List<Tarea> {
        return tareas.filter { !it.estaCompletada }
    }

    fun contarTareasPendientes(): Int {
        return tareas.count { !it.estaCompletada }
    }

    fun obtenerTodasLasTareas(): List<Tarea> {
        return tareas.toList()
    }
}