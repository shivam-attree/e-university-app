package com.example.shivam.eschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class profile_teacher extends AppCompatActivity {
    private TextView tv_username,tv_pass,tv_address,tv_uni,tv_name,tv_surname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_teacher);
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);

        /*if(!BackgroundWorker.getInstance(this).isLoggedIn()){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }*/
        tv_username=(TextView)findViewById(R.id.tv_userName1);
        tv_pass=(TextView)findViewById(R.id.tv_pass1);
        tv_name=(TextView)findViewById(R.id.tv_name1);
        tv_address=(TextView)findViewById(R.id.tv_address);
        tv_uni=(TextView)findViewById(R.id.tv_uni);
        tv_surname=(TextView)findViewById(R.id.tv_surname1);
       String username="teacher";
       String password="check";
       String name="shivam";
       String surname="attree";
       String address="VIT university,chennai";
       String university="VIT University";
                //textViewUsername.setText(BackgroundWorker.getInstance(this).getUsername(getApplicationContext()));
        tv_pass.setText("Password: "+password);
        tv_username.setText("Username: "+ username);
        tv_address.setText("Address: "+address);
        tv_name.setText("Name: "+name);
        tv_surname.setText("Sur-Name: "+surname);
        tv_uni.setText("University: "+university);



    }
}
