package ni.edu.uam.pU

import ni.edu.uam.pU.models.GestorTareas
import ni.edu.uam.pU.models.Tarea
import org.junit.Assert.assertTrue
import org.junit.Test

class EliminarTareaTest {
    @Test
    fun eliminarTarea_desapareceDeLaLista() {
        val gestor = GestorTareas()
        val tarea = Tarea(1, "Prueba", "Descripción")
        gestor.agregarTarea(tarea)
        
        gestor.eliminarTarea(1)
        
        assertTrue(gestor.obtenerTodasLasTareas().isEmpty())
    }
}
