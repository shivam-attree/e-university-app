package com.example.shivam.eschool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.EditText;

public class register_marks extends AppCompatActivity{
    EditText m1,m2,m3,re;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_marks);
        m1 = (EditText) findViewById(R.id.marks1);
        m2 = (EditText) findViewById(R.id.marks2);
        m3 = (EditText) findViewById(R.id.marks3);
        re=(EditText)findViewById(R.id.r);
        }
    public int OnSubmit(View view){
        String mark1 = m1.getText().toString();
        int m1=Integer.valueOf(mark1);
        String mark2 =  m2.getText().toString();
        int m2= Integer.valueOf(mark2);
        String mark3 = m3.getText().toString();
        int m3=Integer.valueOf(mark3);
        String registration= re.getText().toString();
        String type = "register1";
        int average=0;
        average=(m1+m2+m3)/3;
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,mark1,mark2,mark3,registration);
        return average;
    }
}
