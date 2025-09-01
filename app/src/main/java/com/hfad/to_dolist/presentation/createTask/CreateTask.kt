package com.hfad.to_dolist.presentation.createTask

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.compose.back
import com.example.compose.purpul
import com.hfad.to_dolist.R
import com.hfad.to_dolist.model.TaskModel
//import com.hfad.to_dolist.navigation.NavGraf
//import com.hfad.to_dolist.navigation.TaskUI
import com.hfad.to_dolist.navigation.TaskUIRoute
import com.hfad.to_dolist.presentation.components.CustomTextField
import com.hfad.to_dolist.presentation.components.DatePickerDialog
import com.hfad.to_dolist.presentation.viewModel.TaskViewModel
import java.time.LocalDate

@Composable
fun CreateTask(
    navController: NavHostController,
    taskViewModel: TaskViewModel
) {
    var title by rememberSaveable { mutableStateOf("") }
    var descripton by rememberSaveable { mutableStateOf("") }
    var localdate by remember { mutableStateOf<LocalDate?>(null) }
    var show by remember { mutableStateOf(false) }
    Image(
        painter = painterResource(id = R.drawable.img1),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .imePadding()
                .clip(shape = RoundedCornerShape(18.dp))
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(18.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = back
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(bottom = 40.dp)

            ) {
                Text(
                    text = "Create Task",
                    style = MaterialTheme.typography.displayLarge,
                    fontSize = 32.sp,
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                )
                CustomTextField(
                    value = title,
                    title = "title",
                    onChange = { title = it }
                )

                CustomTextField(
                    value = descripton,
                    title = "descripton",
                    onChange = {
                        descripton = it

                    }
                )
                Row {
                    Button(
                        modifier = Modifier
                            .padding(top = 18.dp, start = 10.dp, end = 10.dp)
                            .height(50.dp),
                        onClick = {
                            show = true

                        }
                    ) {
                        Text(
                            text = "Select Data"
                        )
                    }
                    Button(
                        modifier = Modifier
                            .padding(top = 18.dp, start = 10.dp, end = 10.dp)
                            .height(50.dp),
                        onClick = {
                            taskViewModel.addTask(
                                taskModel = TaskModel(
                                    id = 0,
                                    title = title,
                                    descripton = descripton,
                                    date = localdate.toString()
                                )

                            )
                            navController.navigate(TaskUIRoute) {
                                popUpTo(TaskUIRoute) { inclusive = true }
                            }
//                            navController.navigate(NavGraf.TaskUI.route) {
//                                popUpTo(NavGraf.TaskUI.route) { inclusive = true }
//
//                            }

                        }
                    ) {
                        Text(
                            text = "Create"
                        )
                    }
                }
                if (show) {
                    DatePickerDialog(
                        onDateSelected = {
                            localdate = it
                            show = false
                        },
                        onDismiss = {
                            show = false
                        }
                    )
                }

            }
        }
    }
}