package ni.edu.uam.pU

import ni.edu.uam.pU.models.GestorTareas
import ni.edu.uam.pU.models.Tarea
import org.junit.Assert.assertEquals
import org.junit.Test

class ContarTareasPendientesTest {
    @Test
    fun contarTareasPendientes_retornaValorCorrecto() {
        val gestor = GestorTareas()
        gestor.agregarTarea(Tarea(1, "Tarea 1", ""))
        gestor.agregarTarea(Tarea(2, "Tarea 2", ""))
        gestor.marcarComoCompletada(1)
        
        val pendientes = gestor.contarTareasPendientes()
        
        assertEquals(1, pendientes)
    }
}
