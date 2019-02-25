package br.com.renannunessil.moviestest.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import br.com.renannunessil.moviestest.data.local.dao.MovieDao;
import br.com.renannunessil.moviestest.model.FavoriteMovie;

@Database(entities = {FavoriteMovie.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "favoriteMovies";
    private static AppDataBase instance;

    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            synchronized (AppDataBase.class) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDataBase.class,
                        DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return instance;
    }

    public abstract MovieDao movieDao();
}
