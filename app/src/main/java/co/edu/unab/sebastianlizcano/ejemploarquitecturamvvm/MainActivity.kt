package co.edu.unab.sebastianlizcano.ejemploarquitecturamvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import co.edu.unab.sebastianlizcano.ejemploarquitecturamvvm.TaskScreen
import co.edu.unab.sebastianlizcano.ejemploarquitecturamvvm.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskScreen(viewModel = viewModel)
        }
    }
}
