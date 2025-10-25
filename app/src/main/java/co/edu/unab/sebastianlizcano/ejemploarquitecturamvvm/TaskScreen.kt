package co.edu.unab.sebastianlizcano.ejemploarquitecturamvvm

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import co.edu.unab.sebastianlizcano.ejemploarquitecturamvvm.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(viewModel: TaskViewModel) {

    val tasks by viewModel.tasks.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TaskFlow - Lista de tareas") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = viewModel.newTask,
                onValueChange = { viewModel.updateTaskText(it) },
                label = { Text("Nueva tarea") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { viewModel.addTask() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(tasks) { task ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(task.title)
                            IconButton(onClick = { viewModel.deleteTask(task) }) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Eliminar tarea"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
