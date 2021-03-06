package com.example.chota5511.testfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();

    //Override method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                Settings();
                break;
            case R.id.action_signup:
                SignUp();
                break;
        }

        return true;
    }
    ///<Override method end>


    //Clicked event
    public void Login(View v){
        EditText userEmail = findViewById(R.id.user_email);
        EditText password = findViewById(R.id.password);

        Login(userEmail.getText().toString(),password.getText().toString());
    }

    public void ForgotPassword(View v){
        ForgotPassword();
    }
    ///<Clicked event end>


    //Process method
    public void Login(String _email, String password){
        final Intent mainActivity = new Intent(this,MainActivity.class);

        auth.signInWithEmailAndPassword(_email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isComplete() == true){
                    Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT);
                    startActivity(mainActivity);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }

    public void ForgotPassword(){
        Intent tmp = new Intent(this, MainActivity.class);

        startActivity(tmp);
        Toast.makeText(getApplicationContext(),"Forgot password", Toast.LENGTH_SHORT).show();
    }

    public void SignUp(){
        Intent tmp = new Intent(this,SignUpActivity.class);
        startActivity(tmp);
        Toast.makeText(getApplicationContext(),"Sign up",Toast.LENGTH_SHORT).show();
    }

    public void Settings(){
        Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
    }
    ///<Process method end>
}
