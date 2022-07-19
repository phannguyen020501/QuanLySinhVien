package com.example.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHandler databaseHandler;

    private List<Student> listStudents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(this);
        listStudents = databaseHandler.getAllStudent();

        for(Student student: listStudents){
            System.out.println(student.getName());
        }


    }
}