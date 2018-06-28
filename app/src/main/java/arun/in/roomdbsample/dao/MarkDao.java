package arun.in.roomdbsample.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import arun.in.roomdbsample.models.Marks;
import arun.in.roomdbsample.models.Student;

@Dao
public interface MarkDao {
    @Insert
    void insert(Marks... marks);

    @Delete
    void delete(Marks... marks);

    @Query("Delete  FROM Marks WHERE mid== :id")
    void delete(int id);
}
