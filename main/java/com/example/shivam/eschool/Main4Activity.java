package com.example.shivam.eschool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity {
    EditText nameEt , schoolEt, regnoEt, mobileNoEt, passwordEt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        nameEt = (EditText)findViewById(R.id.editText);
        schoolEt = (EditText)findViewById(R.id.editText4);
        regnoEt = (EditText)findViewById(R.id.editText7);
        mobileNoEt = (EditText)findViewById(R.id.editText9);
        passwordEt = (EditText)findViewById(R.id.editText10);
    }
    public void register(View v){
        String name = nameEt.getText().toString();
        String school =schoolEt.getText().toString();
        String regno =regnoEt.getText().toString();
        String mobileNo =mobileNoEt.getText().toString();
        String password =passwordEt.getText().toString();
        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,name,school,regno,mobileNo,password);
    }
}
