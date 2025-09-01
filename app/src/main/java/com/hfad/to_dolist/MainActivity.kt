package com.hfad.to_dolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.AppTheme
import com.hfad.to_dolist.navigation.Navigation
import com.hfad.to_dolist.presentation.viewModel.TaskViewModel
import com.hfad.to_dolist.presentation.viewModel.TaskViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //Фиксирует ориентацию экрана в портретном режиме

//        WindowCompat.setDecorFitsSystemWindows(window, false) //Позволяет контенту приложения отображаться под системными барами
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val taskViewModel: TaskViewModel = viewModel(
                    factory = TaskViewModelFactory((application as ToDoApplication).repository)
                )
                Surface {
                    Navigation(taskViewModel)
                }
            }
        }
    }
}
//WindowCompat.setDecorFitsSystemWindows(window, false) - для того что б экран не переворачивался