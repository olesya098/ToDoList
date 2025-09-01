package com.hfad.to_dolist.repository

import com.hfad.to_dolist.dao.TaskDao
import com.hfad.to_dolist.model.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository (
    private val taskDao: TaskDao
){
    val allTask: Flow<List<TaskEntity>> = taskDao.getAll()

    suspend fun insert(taskEntity: TaskEntity){
        taskDao.insert(taskEntity)
    }
    suspend fun delete(taskEntity: TaskEntity){
        taskDao.delete(taskEntity)
    }


}
