package com.example.shivam.eschool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    EditText eregno,ename,esurname,eusername,epassword,ebranch,esem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ename = (EditText) findViewById(R.id.e_fname);
        esurname = (EditText) findViewById(R.id.e_lname);
        eregno = (EditText) findViewById(R.id.e_regno);
        ebranch = (EditText) findViewById(R.id.e_branch);
        eusername = (EditText) findViewById(R.id.e_uname);
        epassword = (EditText) findViewById(R.id.e_pwd);
        esem = (EditText) findViewById(R.id.e_sem);
    }

    public void OnReg(View view){
        String regno = eregno.getText().toString();
        String name =  ename.getText().toString();
        String surname = esurname.getText().toString();
        String username =  eusername.getText().toString();
        String password = epassword.getText().toString();
        String branch =  ebranch.getText().toString();
        String sem = esem.getText().toString();
        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,regno,name,surname,username,password,branch,sem);
    }

}
