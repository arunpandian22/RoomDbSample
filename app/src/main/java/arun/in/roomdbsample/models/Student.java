package arun.in.roomdbsample.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class Student
{
  public   String Name;
  @PrimaryKey(autoGenerate = true)
  public   int id;
   public String Address;
   public boolean isMale;
   public Date createdAt;
   @Embedded
  public   Marks marks;
}
