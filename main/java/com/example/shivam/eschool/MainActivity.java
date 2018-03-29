package com.example.shivam.eschool;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText usernameEt, passwordEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEt = (EditText) findViewById(R.id.editText);
        passwordEt = (EditText) findViewById(R.id.editText2);

    }
    public void OnLogin(View view){
        String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();
        if(username.equals("teacher")&&password.equals("check"))
        {
            Intent intent=new Intent(MainActivity.this,Main3Activity.class);
            startActivity(intent);
        }
        else {
            String type = "login";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, username, password);
        }
    }

    public void OpenReg(View view){

        startActivity(new Intent(this,Register.class));
    }

}
