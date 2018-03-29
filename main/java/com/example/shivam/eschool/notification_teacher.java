package com.example.shivam.eschool;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;



public class notification_teacher extends AppCompatActivity {
    EditText ed,reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_teacher);
        ed = (EditText) findViewById(R.id.noti);
        reg=(EditText) findViewById(R.id.re);

            }
    public void onsubmit(View view){
        String type = "register2";
        String notification=ed.getText().toString();
        String regi=reg.getText().toString();
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,notification,regi);

    }
}