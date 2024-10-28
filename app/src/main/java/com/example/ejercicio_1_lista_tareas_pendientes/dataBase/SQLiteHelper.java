package com.example.ejercicio_1_lista_tareas_pendientes.dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ejercicio_1_lista_tareas_pendientes.dataBase.dao.TaskDao;
import com.example.ejercicio_1_lista_tareas_pendientes.domain.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class SQLiteHelper extends RoomDatabase {

    private static SQLiteHelper instance;

    public abstract TaskDao taskDao();

    public static synchronized SQLiteHelper getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            SQLiteHelper.class, "tasksDB")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
