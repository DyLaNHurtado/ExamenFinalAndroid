package com.example.examenfinalandroid.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.examenfinalandroid.model.CategoriaEntity;
import com.example.examenfinalandroid.model.RecetaEntity;


/*  Clase de la BBDD, como tenemos dos entidades/dos tablas, habra que escribir ambas, asi como generar ambos Dao

    La version se cambiara cuando hagamos un cambio en alguna entidad [por ejemplo,
    cambiar el nombre a una variable] y ya hayamos instanciado la BBDD en algun momento + ejecutado la app.

    No parece necesario cambiar la version si se agrega algun metodo mas a algun Dao, aunque señalice un
    error/aviso, al iniciar la app se actualiza.
 */
@Database(entities = {CategoriaEntity.class, RecetaEntity.class}, version = 1)
public abstract class DataRoomDB extends RoomDatabase {

    private static String DATABASE_NAME = "basededatos";

    public abstract CategoriaDao categoriaDao();

    public abstract RecetaDao recetaDao();

    private static volatile DataRoomDB INSTANCE;

    public synchronized static DataRoomDB getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    DataRoomDB.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
