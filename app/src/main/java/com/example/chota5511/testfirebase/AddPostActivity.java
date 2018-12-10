package com.example.chota5511.testfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.Inflater;

public class AddPostActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
    }

    public void Back(View v){
        finish();
    }

    public void AddPost(View v){
        EditText content = findViewById(R.id.et_content);

        Post tmp = new Post(user.getEmail(),Calendar.getInstance().getTime(),content.getText().toString());

        if(tmp.SaveChange()==true){
            Toast.makeText(getApplicationContext(),"Post success",Toast.LENGTH_SHORT);
        }
        else {
            Toast.makeText(getApplicationContext(),"Post failed",Toast.LENGTH_SHORT);
        }
        finish();
    }

}
