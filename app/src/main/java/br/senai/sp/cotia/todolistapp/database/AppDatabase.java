package br.senai.sp.cotia.todolistapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.senai.sp.cotia.todolistapp.model.Tarefa;

@Database(entities = {Tarefa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //variavel para acessar a database
    private static AppDatabase database;
    //metodo para tarefaDao
    public abstract TarefaDao getTarefaDao();

    public static AppDatabase getDatabase(Context context){
        //verifico se a database é nula
        if (database == null){
            //instancia a database
            database = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "todolist").build();
        }
        //retorna a database
        return database;
    }
}
