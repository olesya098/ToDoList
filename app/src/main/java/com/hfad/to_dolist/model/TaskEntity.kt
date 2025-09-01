package com.hfad.to_dolist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")// называем таблицу
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,//первичный ключ для связки(id кооторый сам увеличивается)
    val title: String,
    val descripton: String,
    val state: Boolean = false,
    val date: String
)
