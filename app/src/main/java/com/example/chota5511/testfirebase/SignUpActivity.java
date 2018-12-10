package com.example.chota5511.testfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void SignUp(View v){
        EditText userID = findViewById(R.id.user_id);
        EditText userName = findViewById(R.id.user_name);
        EditText password = findViewById(R.id.password);
        User tmpUser = new User(userID.getText().toString(),userName.getText().toString(),password.getText().toString());
        if(tmpUser.SaveChange() == true){
            Toast.makeText(getApplicationContext(),"Sign Up Success",Toast.LENGTH_SHORT).show();
            Intent loginActivity = new Intent(this,LoginActivity.class);
            startActivity(loginActivity);
        }
        else {
            Toast.makeText(getApplicationContext(),"Sign Up Failed",Toast.LENGTH_SHORT).show();
        }

    }

}
