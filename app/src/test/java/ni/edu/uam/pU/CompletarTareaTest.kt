package ni.edu.uam.pU

import ni.edu.uam.pU.models.GestorTareas
import ni.edu.uam.pU.models.Tarea
import org.junit.Assert.assertTrue
import org.junit.Test

class CompletarTareaTest {
    @Test
    fun completarTarea_cambiaEstadoACompletada() {
        val gestor = GestorTareas()
        val tarea = Tarea(1, "Prueba", "Descripción")
        gestor.agregarTarea(tarea)
        
        gestor.marcarComoCompletada(1)
        
        val tareaResult = gestor.obtenerTodasLasTareas().find { it.id == 1 }
        assertTrue(tareaResult?.estaCompletada ?: false)
    }
}
