package co.edu.unab.sebastianlizcano.ejemploarquitecturamvvm

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class TaskRepository @Inject constructor(private val dao: TaskDao) {

    fun getAllTasks(): Flow<List<Task>> = dao.getAllTasks()

    suspend fun insertTask(task: Task) = dao.insert(task)

    suspend fun deleteTask(task: Task) = dao.delete(task)
}
