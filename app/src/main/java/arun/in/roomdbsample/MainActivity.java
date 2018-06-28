package arun.in.roomdbsample;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.amitshekhar.DebugDB;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import arun.in.roomdbsample.localdb.AppDataBase;
import arun.in.roomdbsample.models.Marks;
import arun.in.roomdbsample.models.Student;
import arun.in.roomdbsample.sharedpref.AppStorage;

public class MainActivity extends AppCompatActivity implements StudentAdapter.StudentAdapterListener {
RecyclerView rvStudentList;
StudentAdapter studentAdapter;
List<Student> studentList;
FloatingActionButton fabAddStudent;
RoomDbApp roomDbApp;
AppDataBase appDataBase;
AppStorage appStorage;
Marks marks;
String TAG="MainActivity";
String[] name={"arun","ashik","ashok","abi","aby","amir","anas","bala","barthi","chandru"};
String[] city={"chennai","chinnalapatti","madurai","tuticudi","palani","royappanpatti","kerala","mumbai","selem","coimbatore"};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvStudentList=(RecyclerView)findViewById(R.id.rvStudentList);
        fabAddStudent=(FloatingActionButton)findViewById(R.id.fabAdd);
        rvStudentList.setLayoutManager(new LinearLayoutManager(this));
        roomDbApp=(RoomDbApp)getApplication();
        appDataBase=roomDbApp.getAppDataBase();
        appStorage=roomDbApp.getAppStorage();
        studentList=new ArrayList<>();
        studentList=appDataBase.studentDao().getAllStudent();
        marks=new Marks();
        marks.sub1=23;
        marks.sub2=53;
        marks.sub3=53;
        Log.d(TAG, "onCreate: debug"+ DebugDB.getAddressLog());
        studentAdapter=new StudentAdapter(this,studentList,this);
        rvStudentList.setAdapter(studentAdapter);
        fabAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               int count= appStorage.getIntegerDetails("count");
               appStorage.setIntegerDetails("count",count+1);
               Student student=new Student();
               student.Name="name"+count;
               if (count==0)
               {
                   student.isMale=true;
               }else{
                   if (count%2==0)
                   student.isMale=true;
                   else
                       student.isMale=false;
               }
               count=count+1;
               student.Address=city[count%city.length];
               student.marks=marks;
               student.createdAt=new Date();
               appDataBase.studentDao().insert(student);
               List<Student> studentList=appDataBase.studentDao().select(true);

               for(Student stud:studentList){
                   Log.d(TAG, stud.Name+": "+stud.isMale);
               }

                upDataeAdapter();
            }
        });

    }


    @Override
    public void onTicketDelete(Student student) {
        appDataBase.studentDao().delete(student);
        upDataeAdapter();
        Log.d(TAG, "onTicketDelete: ");
    }

    @Override
    public void onTicketUpdate(Student student)
    {
        if (student.Address.equals(city[(student.id-1)%city.length]))
        {
            student.Address=city[(student.id)%city.length];
        }else {
            student.Address=city[(student.id-1)%city.length];
        }
        appDataBase.studentDao().update(student);
        upDataeAdapter();
        Log.d(TAG, "onTicketUpdate: ");

    }

    public void upDataeAdapter(){
        studentAdapter.upDate(appDataBase.studentDao().getAllStudent());
    }
}
