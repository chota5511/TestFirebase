package com.example.chota5511.testfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void SignUp(View v){
        final EditText userEmail = findViewById(R.id.user_email);
        final EditText userName = findViewById(R.id.user_name);
        final EditText password = findViewById(R.id.password);

        final Intent loginActivity = new Intent(this, LoginActivity.class);

        OnCompleteListener<AuthResult> result = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    auth.signInWithEmailAndPassword(userEmail.getText().toString(),password.getText().toString());

                    //Get user from firebase
                    FirebaseUser user = auth.getCurrentUser();

                    //Set user name
                    UserProfileChangeRequest userProfile = new UserProfileChangeRequest.Builder().setDisplayName(userName.getText().toString()).build();

                    //Update user profile
                    user.updateProfile(userProfile);

                    //return a success Toast message
                    Toast.makeText(getApplicationContext(),"Sign up success",Toast.LENGTH_SHORT).show();
                    startActivity(loginActivity);
                }
                else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        };
        auth.createUserWithEmailAndPassword(userEmail.getText().toString(),password.getText().toString()).addOnCompleteListener(result);
    }

}
