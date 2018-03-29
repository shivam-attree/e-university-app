package com.example.shivam.eschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by mukul on 4/20/2017.
 */

public class reg_teacher_marks extends AppCompatActivity

    {
        EditText reget;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_teacher_marks);
        reget = (EditText) findViewById(R.id.editText);

        }
    public void onlogin(View view){
        String registration = reget.getText().toString();
        String type = "login1";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, registration);
        //Intent intent=new Intent(this,grade.class);
        //startActivity(intent);
        }



}

