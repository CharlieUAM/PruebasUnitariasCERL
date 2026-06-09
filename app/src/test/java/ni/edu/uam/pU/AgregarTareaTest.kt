package ni.edu.uam.pU

import ni.edu.uam.pU.models.GestorTareas
import ni.edu.uam.pU.models.Tarea
import org.junit.Assert.assertEquals
import org.junit.Test

class AgregarTareaTest {
    @Test
    fun agregarTarea_incrementaTamanioLista() {
        val gestor = GestorTareas()
        val tarea = Tarea(1, "Prueba", "Descripción")
        
        gestor.agregarTarea(tarea)
        
        assertEquals(1, gestor.obtenerTodasLasTareas().size)
    }
}
