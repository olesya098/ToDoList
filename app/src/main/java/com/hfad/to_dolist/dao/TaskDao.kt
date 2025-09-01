package com.hfad.to_dolist.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hfad.to_dolist.model.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY id DESC  ")
    fun getAll(): Flow<List<TaskEntity>>

    @Insert
    fun insert(taskEntity: TaskEntity)

    @Delete
    fun delete(taskEntity: TaskEntity)


}
