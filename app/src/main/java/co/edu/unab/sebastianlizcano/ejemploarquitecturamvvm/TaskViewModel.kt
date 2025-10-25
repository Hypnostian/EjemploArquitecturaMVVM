package co.edu.unab.sebastianlizcano.ejemploarquitecturamvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import co.edu.unab.sebastianlizcano.ejemploarquitecturamvvm.Task
import co.edu.unab.sebastianlizcano.ejemploarquitecturamvvm.TaskRepository

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    // LiveData que observa los cambios en la base de datos
    val tasks = repository.getAllTasks().asLiveData()

    // Estado de la nueva tarea que se escribe en el campo de texto
    var newTask by mutableStateOf("")

    // Actualiza el texto del campo de tarea nueva
    fun updateTaskText(text: String) {
        newTask = text
    }

    // Agrega una nueva tarea a la base de datos
    fun addTask() {
        if (newTask.isNotBlank()) {
            viewModelScope.launch {
                repository.insertTask(Task(title = newTask))
                newTask = "" // limpia el campo después de agregar
            }
        }
    }

    // Elimina una tarea existente
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}
