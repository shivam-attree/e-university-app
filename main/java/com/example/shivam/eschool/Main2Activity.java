package com.example.shivam.eschool;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

String reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            reg = extras.getString("regno");
            // and get whatever type user account id is
        }
    }
    public void profile(View v){
        Intent intent=new Intent(this ,profile.class);
        startActivity(intent);
    }
    public void student_result(View view){
        Intent intent=new Intent(this,grade.class);
        intent.putExtra("regno",reg);
        startActivity(intent);
    }
    public void notification(View view){
        Intent intent=new Intent(this,Notification_student.class);
        startActivity(intent);
    }


}

