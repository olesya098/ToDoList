package com.hfad.to_dolist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hfad.to_dolist.presentation.createTask.CreateTask
import com.hfad.to_dolist.presentation.taskUI.TaskUI
import com.hfad.to_dolist.presentation.viewModel.TaskViewModel

//@Composable
//fun Navigation(taskViewModel: TaskViewModel) {
//    val navController = rememberNavController()
//    NavHost(
//        navController = navController,
//        startDestination = NavGraf.TaskUI.route
//    ) {
//        composable(NavGraf.TaskUI.route) {
//            TaskUI(navController, taskViewModel)
//        }
//        composable(NavGraf.CreateTask.route) {
//            CreateTask(navController,taskViewModel)
//        }
//    }
//}
@Composable
fun Navigation(taskViewModel: TaskViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TaskUIRoute
    ) {
        composable<TaskUIRoute> {
            TaskUI(navController, taskViewModel)
        }
        composable<CreateTaskRoute> {
            CreateTask(navController, taskViewModel)
        }
    }
}