package com.hfad.to_dolist.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.to_dolist.model.TaskEntity
import com.hfad.to_dolist.model.TaskModel
import com.hfad.to_dolist.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    val allTasks = taskRepository.allTask
    private val _task = MutableStateFlow<List<TaskModel>?>(null)
    val task = _task.asStateFlow()


    fun addTask(taskModel: TaskModel) {
        viewModelScope.launch {
//            taskRepository.insert(
//                taskEntity = TaskEntity(
//                    title = taskModel.title,
//                    descripton = taskModel.descripton,
//                    state = taskModel.state,
//                    date = taskModel.date
//                )
//            )
            val newTask = _task.value?.toMutableList() ?: mutableListOf()
            newTask.add(
                TaskModel(
                    id = (task.value?.size ?: 0) + 1,
                    title = taskModel.title,
                    descripton = taskModel.descripton,
                    state = taskModel.state,
                    date = taskModel.date
                )
            )
            _task.value = newTask
        }

    }

    fun editTask(updatedTask: TaskModel) {
        viewModelScope.launch {
            _task.value = _task.value?.map {
                if (it.id == updatedTask.id) {
                    updatedTask
                } else {
                    it
                }
            }
        }


    }


    fun deleteTask(delete: TaskModel) {
        viewModelScope.launch {
//            taskRepository.delete(
//                taskEntity = TaskEntity(
//                    title = delete.title,
//                    descripton = delete.descripton,
//                    state = delete.state,
//                    date = delete.date
//
//                )
//            )
            val newTask = _task.value?.toMutableList() ?: mutableListOf()
            newTask.remove(delete)
            _task.value = newTask
        }
    }

    //статус выполнения
    fun status(taskModel: TaskModel) {
        viewModelScope.launch {
            _task.value = _task.value?.map { //получаем статус и записываем в изменяемый список map
                if (it.id == taskModel.id) {
                    //it - текущий элемент списка в лямбда-выражении .map {}
                    //it.id - ID текущей задачи в списке
                    //taskModel.id - ID задачи, которую нужно изменить
                    it.copy(state = !it.state)
                    //it - это текущий элемент коллекции в функции map
                    //.copy() - это функция копирования данных класса (обычно используется с data class в Kotlin)
                    //state = !it.state - создаёт новую копию объекта, где поле state инвертировано
                } else {
                    it
                }
            }
        }
    }


}