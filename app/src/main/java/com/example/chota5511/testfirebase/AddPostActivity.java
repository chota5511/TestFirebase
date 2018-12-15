package com.example.chota5511.testfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.Inflater;

public class AddPostActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    //Overridde method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
    }
    ///<Override method end>

    //Click event
    public void Back(View v){
        finish();
    }

    public void AddPost(View v){
        EditText content = findViewById(R.id.et_content);
        AddPost(user.getUid(),content.getText().toString());
        finish();
    }
    ///<Click event end>

    //Process method
    public void AddPost(String _userID, String _content){
        Post tmp = new Post(_userID, Calendar.getInstance().getTime(), _content);

        if(tmp.SaveChange()==true){
            Toast.makeText(getApplicationContext(),"Post success",Toast.LENGTH_SHORT);
        }
        else {
            Toast.makeText(getApplicationContext(),"Post failed",Toast.LENGTH_SHORT);
        }
    }
    ///<Process method end>
}
