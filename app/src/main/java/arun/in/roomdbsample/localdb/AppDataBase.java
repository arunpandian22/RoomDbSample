package arun.in.roomdbsample.localdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import arun.in.roomdbsample.dao.MarkDao;
import arun.in.roomdbsample.dao.StudentDao;
import arun.in.roomdbsample.models.Marks;
import arun.in.roomdbsample.models.Student;
import arun.in.roomdbsample.typeConverters.Converters;

@Database(entities = {Student.class, Marks.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance;
    public abstract MarkDao markDao();
    public abstract StudentDao studentDao();
    public static AppDataBase getAppDatabase(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class,
                    "roomdb")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    //        .addMigrations(MIGRATION_1_2,MIGRATION_2_3)


}