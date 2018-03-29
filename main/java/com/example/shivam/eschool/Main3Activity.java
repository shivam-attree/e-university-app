package com.example.shivam.eschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }
    public void students(View v){
        Intent intent=new Intent(this ,student.class);
        startActivity(intent);
    }
    public void student_marks(View view){
        Intent intent=new Intent(this,student_marks.class);
        startActivity(intent);
    }
    public void generate_results(View view){
        Intent intent=new Intent(this,generate_result.class);
        startActivity(intent);
    }
    public void profile_teacher(View view){
        Intent intent=new Intent(this,profile_teacher.class);
        startActivity(intent);
    }
    public void notification_teacher(View view){
        Intent intent=new Intent(this,notification_teacher.class);
        startActivity(intent);
    }
}
