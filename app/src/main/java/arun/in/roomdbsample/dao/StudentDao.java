package arun.in.roomdbsample.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arun.in.roomdbsample.models.Student;

@Dao
public interface StudentDao
{
    @Insert
    void insert(Student... address);

    @Delete
    void delete(Student... address);

    @Update
       void update(Student... student);

    @Query("Delete  FROM Student WHERE id== :id")
    void delete(int id);

    @Query("Select * FROM Student WHERE isMale== :isTrue")
    List<Student> select(boolean isTrue);

    @Query("Select * FROM Student")
    List<Student> getAllStudent();

}
