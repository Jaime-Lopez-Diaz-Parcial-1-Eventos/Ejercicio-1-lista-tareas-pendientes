package com.example.ejercicio_1_lista_tareas_pendientes.dataBase.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ejercicio_1_lista_tareas_pendientes.domain.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void addTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("SELECT * FROM tasks WHERE isCompleted = :isCompleted")
    List<Task> getTasks(boolean isCompleted);
}
