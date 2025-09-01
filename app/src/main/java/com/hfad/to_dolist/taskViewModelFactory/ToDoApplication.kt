package com.hfad.to_dolist

import android.app.Application
import com.hfad.to_dolist.database.TaskDatabase
import com.hfad.to_dolist.repository.TaskRepository

class ToDoApplication : Application() {
    val database: TaskDatabase by lazy {
        TaskDatabase.getDatabase(this)
    }
    val repository: TaskRepository by lazy {
        TaskRepository(database.taskDao())
    }
}