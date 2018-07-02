# RoomDbSample
Basic CRUD operations in RoomDB
This is a basic demo app on how to implement RoomDb 

## output


![Watch the video](https://media.giphy.com/media/9M1qqgTBB0m44bEmXd/giphy.gif)



    
   ## Dependency
```
   def room_version = "1.1.1"
    implementation "android.arch.persistence.room:runtime:$room_version"
    annotationProcessor "android.arch.persistence.room:compiler:$room_version"
```
Here annotationProcessor is used to use annotation in RoomDb like following
 ```
    @Insert
    void insert(Student... students);

    @Delete
    void delete(Student... students);

    @Update
    void update(Student... students);
 
 ```
 
 ## Creating Model for Tables:
 
 @Entity annotation is used to define the table. 
 @Entity(tableName = "student") here table name defines the name of the table.
 if you didn't mention the tableName in @Entity means it  will take a class name as a table name
 
```

@Entity(tableName = "student")
public class Student
{
  public String Name;
  @PrimaryKey(autoGenerate = true)
  public int id;
  public String Address;
  public boolean isMale;
  public Date createdAt;
  @Embedded
  public Marks marks;
}
```

here  @Embedded annotation is used to include the Marks class properties as a fields in table

```
  @PrimaryKey(autoGenerate = true)
  public   int id;
  
```

@PrimaryKey annotation is used to define the unique values
if you give autogenerae= true means it will create the id for each coloumn.

  ```
   @ColumnInfo(name = "mid")
   public int studentId;
   
  ```
  
a @ColumnInfo annotation used to define the column name if you didn't prove any column name means it will take a variable   name   as a coloumn name
  
  
##  TypeConverters 
 RoomDb not supporting the some data Types. for example Date, List type.
  but Using @TypeConverter annotation we can define to convert the type to another type. then automatically it will convert the types when creating and updating.
   
Here I am converting date type to long then long to date.

```
public class Converters
{
    @TypeConverter
    public static Date fromTimestamp(Long value)
    {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date)
    {
        return date == null ? null : date.getTime();
    }

}

```
Here I convert the Date type to Long type 

## Create a DAO class

This is an interface which acts is an intermediary between the user and the database. All the operation to be performed on a table has to be defined here. We define the list of operation that we would like to perform on table

```

@Dao
public interface StudentDao
{
    @Insert
    void insert(Student... students);

    @Delete
    void delete(Student... students);

    @Update
       void update(Student... students);

    @Query("Delete  FROM Student WHERE id== :id")
    void delete(int id);

    @Query("Select * FROM Student WHERE isMale== :isTrue")
    List<Student> select(boolean isTrue);

    @Query("Select * FROM Student")
    List<Student> getAllStudent();

}

```

## Create Database class
class where you define all the entities that means all the tables that you want to create for that database.your database version and if you define any typeconverter  means you have to define here only
like following:

```
@Database(entities = {Student.class, Marks.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {

}

```

after creating the Database class you have to build the Database like following in this class

```
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
    
```

## Data Migration

if you change alter the table means or you added any new table means you have to increase the version number and add the migration methods for the data Migrations. otherwise you will all old data will be lost.
you have create migration method and in Database builder like following:

```
Room.databaseBuilder(getApplicationContext(), MyDb.class, "database-name")
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3).build();

static final Migration MIGRATION_1_2 = new Migration(1, 2) {
    @Override
    public void migrate(SupportSQLiteDatabase database) {
        database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
                + "`name` TEXT, PRIMARY KEY(`id`))");
    }
};

static final Migration MIGRATION_2_3 = new Migration(2, 3) {
    @Override
    public void migrate(SupportSQLiteDatabase database) {
        database.execSQL("ALTER TABLE Book "
                + " ADD COLUMN pub_year INTEGER");
    }
};

```

for complete [reference](https://developer.android.com/topic/libraries/architecture/room).


