package com.hfad.to_dolist.presentation.taskUI

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.compose.back
import com.hfad.to_dolist.R
import com.hfad.to_dolist.navigation.CreateTaskRoute
import com.hfad.to_dolist.presentation.createTask.CreateTask
//import com.hfad.to_dolist.navigation.NavGraf
import com.hfad.to_dolist.presentation.task.Task
import com.hfad.to_dolist.presentation.viewModel.TaskViewModel

@Composable
fun TaskUI(
//    navController: NavController,
    navController: NavHostController,
    taskViewModel: TaskViewModel
) {
    val tasks = taskViewModel.task.collectAsState()
    val context = LocalContext.current
    Image(
        painter = painterResource(id = R.drawable.img1),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 40.dp)

    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            tasks.value?.let {
                items(it) { task ->
                    Task(
                        taskModel = task,
                        taskViewModel = taskViewModel,
                        onDeleteClick = {
                            taskViewModel.deleteTask(
                                task
                            )
                        },
                        onStateChange = {
                            taskViewModel.status(
                                task
                            )

                        },
                    )
                }
            } ?: run {
                Toast.makeText(context, "Добавьте заметку!", Toast.LENGTH_LONG).show()
            }
        }

        FloatingActionButton(
            onClick = {
                navController.navigate(CreateTaskRoute)
//                navController.navigate(NavGraf.CreateTask.route)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }

    }

}