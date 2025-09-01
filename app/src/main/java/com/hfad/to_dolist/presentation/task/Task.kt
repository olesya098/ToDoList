package com.hfad.to_dolist.presentation.task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.back
import com.hfad.to_dolist.R
import com.hfad.to_dolist.model.TaskModel
import com.hfad.to_dolist.presentation.components.CustomAltDialog
import com.hfad.to_dolist.presentation.viewModel.TaskViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun Task(
    taskModel: TaskModel,
    taskViewModel: TaskViewModel,
    onDeleteClick: () -> Unit,
    onStateChange: (TaskModel) -> Unit,
) {
    var edit by rememberSaveable { mutableStateOf(false) }
    var editedTitle by rememberSaveable { mutableStateOf(taskModel.title) }
    var editedDescription by rememberSaveable { mutableStateOf(taskModel.descripton) }
    Card(
        modifier = Modifier
            .wrapContentSize()
            .clip(shape = RoundedCornerShape(18.dp))
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(18.dp)
            )

    ) {
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(18.dp))
                .fillMaxWidth()
                .background(back)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = taskModel.state,
                    onCheckedChange = {
                        onStateChange(taskModel)
                    },
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = taskModel.title,
                        fontSize = 22.sp,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            textDecoration = if (taskModel.state) {
                                TextDecoration.LineThrough
                            } else {
                                TextDecoration.None
                            }
                        )
                    )

                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.primary,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )

                    Text(
                        text = taskModel.descripton,
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.displayLarge.copy(
                            textDecoration = if (taskModel.state) {
                                TextDecoration.LineThrough
                            } else {
                                TextDecoration.None
                            }
                        )
                    )
                }

                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(onClick = {
                                edit = true

                            })
                    )


                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(onClick = onDeleteClick)
                    )

                }
            }

            Text(
                text = taskModel.date,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.End)
            )
        }

        if (edit) {
            CustomAltDialog(
                title = editedTitle,
                descripton = editedDescription,
                onDismiss = { edit = false },
                onConfirm = {
                    val updatedTask = taskModel.copy(
                        title = editedTitle,
                        descripton = editedDescription
                    )
                    taskViewModel.editTask(updatedTask)
                    edit = false

                },
                onTitle = {editedTitle = it},
                onDescription = {editedDescription = it}
            )
        }
    }
}
