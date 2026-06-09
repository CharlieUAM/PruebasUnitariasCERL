package ni.edu.uam.pU

import ni.edu.uam.pU.models.GestorTareas
import org.junit.Assert.assertEquals
import org.junit.Test

class ListaVaciaTest {
    @Test
    fun listaVacia_retornaCeroPendientes() {
        val gestor = GestorTareas()
        
        val pendientes = gestor.contarTareasPendientes()
        
        assertEquals(0, pendientes)
    }
}
