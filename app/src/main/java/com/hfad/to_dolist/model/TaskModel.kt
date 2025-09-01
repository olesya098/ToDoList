package com.hfad.to_dolist.model

data class TaskModel(
    val id: Int,
    val title: String,
    val descripton: String,
    val state: Boolean = false,
    val date: String
) {
}