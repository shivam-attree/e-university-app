package com.example.shivam.eschool;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class profile extends AppCompatActivity {
private TextView tv_username,tv_pass,tv_regno,tv_sem,tv_name,tv_branch,tv_surname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);

        if(!BackgroundWorker.getInstance(this).isLoggedIn()){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        tv_username=(TextView)findViewById(R.id.tv_userName);
        tv_pass=(TextView)findViewById(R.id.tv_pass);
        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_branch=(TextView)findViewById(R.id.tv_branch);
        tv_sem=(TextView)findViewById(R.id.tv_sem);
        tv_regno=(TextView)findViewById(R.id.tv_regno);
        tv_surname=(TextView)findViewById(R.id.tv_surname);

        //textViewUsername.setText(BackgroundWorker.getInstance(this).getUsername(getApplicationContext()));
        tv_pass.setText("Password: "+BackgroundWorker.getInstance(this).getUserEmail(getApplicationContext()));
        tv_branch.setText("Branch: "+BackgroundWorker.getString(getApplicationContext(),BackgroundWorker.KEY_BRANCH));
        tv_sem.setText("Semester: "+BackgroundWorker.getString(getApplicationContext(),BackgroundWorker.KEY_SEM));
        tv_username.setText("User Name: "+BackgroundWorker.getString(getApplicationContext(),BackgroundWorker.KEY_USERNAME));
        tv_name.setText("Name: "+BackgroundWorker.getString(getApplicationContext(),BackgroundWorker.KEY_NAME));
        tv_regno.setText("Reg no: "+BackgroundWorker.getString(getApplicationContext(),BackgroundWorker.KEY_REGNO));
        tv_surname.setText("Surname: "+BackgroundWorker.getString(getApplicationContext(),BackgroundWorker.KEY_SURNAME));



    }
}
