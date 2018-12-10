package com.example.chota5511.testfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void Login(View v){
        EditText userID = findViewById(R.id.user_id);
        EditText password = findViewById(R.id.password);
        User tmpUser = new User();
        tmpUser.fetchUser(userID.getText().toString());
        int i = 0;
        while(tmpUser.getPassword().isEmpty() == true && i<2){
            try{
                Thread.sleep(5000);
            }
            catch(InterruptedException e){}
            i++;
        }
        if(tmpUser.getPassword().isEmpty() == false){
            Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT);
        }
        else {
            Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT);
        }
    }

    public void SignUp(MenuItem mi){
        Intent tmp = new Intent(this,SignUpActivity.class);
        startActivity(tmp);
        Toast.makeText(getApplicationContext(),"Sign up",Toast.LENGTH_SHORT).show();
    }

    public void Setting(MenuItem mi){
        MainActivity tmp = new MainActivity();
        tmp.Setting(mi);
    }

    public void ForgotPassword(View v){
        Intent tmp = new Intent(this, MainActivity.class);

        startActivity(tmp);
        Toast.makeText(getApplicationContext(),"Forgot password", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
}
