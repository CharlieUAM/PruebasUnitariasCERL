package ni.edu.uam.pU

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ni.edu.uam.pU.models.Tarea
import ni.edu.uam.pU.models.GestorTareas
import ni.edu.uam.pU.ui.theme.PunitariasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PunitariasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TareaScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TareaScreen(modifier: Modifier = Modifier) {
    val gestorTareas = remember { GestorTareas() }
    var listaTareas by remember { mutableStateOf(gestorTareas.obtenerTodasLasTareas()) }
    var nuevoTitulo by remember { mutableStateOf("") }
    var idCounter by remember { mutableIntStateOf(1) }

    val tareasPendientes = gestorTareas.contarTareasPendientes()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Gestor de Tareas",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = nuevoTitulo,
            onValueChange = { nuevoTitulo = it },
            label = { Text("Título de la tarea") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (nuevoTitulo.isNotBlank()) {
                    val nuevaTarea = Tarea(
                        id = idCounter++,
                        titulo = nuevoTitulo,
                        descripcion = ""
                    )
                    gestorTareas.agregarTarea(nuevaTarea)
                    listaTareas = gestorTareas.obtenerTodasLasTareas()
                    nuevoTitulo = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Agregar Tarea")
        }

        HorizontalDivider()

        Text(
            text = "Tareas pendientes: $tareasPendientes",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listaTareas, key = { it.id }) { tarea ->
                TareaItem(
                    tarea = tarea,
                    onCompletadaChange = {
                        gestorTareas.marcarComoCompletada(tarea.id)
                        listaTareas = gestorTareas.obtenerTodasLasTareas()
                    },
                    onEliminar = {
                        gestorTareas.eliminarTarea(tarea.id)
                        listaTareas = gestorTareas.obtenerTodasLasTareas()
                    }
                )
            }
        }
    }
}

@Composable
fun TareaItem(
    tarea: Tarea,
    onCompletadaChange: (Boolean) -> Unit,
    onEliminar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (tarea.estaCompletada) 
                MaterialTheme.colorScheme.surfaceVariant 
            else 
                MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Checkbox(
                    checked = tarea.estaCompletada,
                    onCheckedChange = onCompletadaChange
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = tarea.titulo,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            IconButton(onClick = onEliminar) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar tarea",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TareaScreenPreview() {
    PunitariasTheme {
        TareaScreen()
    }
}
