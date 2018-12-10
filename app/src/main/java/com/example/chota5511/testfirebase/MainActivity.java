package com.example.chota5511.testfirebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    ValueEventListener postValueEventListener;
    ValueEventListener getUserNameValueEventListener;
    Query queryRefInitial = db.getReference().child("post").orderByChild("date").limitToLast(20);
    Query queryRef = db.getReference().child("post").orderByChild("date").limitToLast(1);
    Query queryUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (user.getUid() == null){
            Intent login = new Intent(this,LoginActivity.class);
            startActivity(login);
        }else {

            postValueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists() == true){
                        for (DataSnapshot d: dataSnapshot.getChildren()){
                            final Post tmpPost = new Post();
                            tmpPost.setPostID(d.getKey());
                            tmpPost.setUserUid(d.child("uid").getValue().toString());
                            tmpPost.setContent(d.child("content").getValue().toString());
                            tmpPost.setDate(d.child("date").getValue(Date.class));

                            getUserNameValueEventListener = new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    LinearLayout postArea = findViewById(R.id.post_area);
                                    postArea.addView(PostToPostBox(tmpPost,dataSnapshot.getValue().toString(),postArea),1);
                                    queryUserName.removeEventListener(getUserNameValueEventListener);   //Remove listener after get user name
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            };

                            //Get user name before add to post box
                            queryUserName = FirebaseDatabase.getInstance().getReference().child("user").child(tmpPost.getUserUid());
                            queryUserName.addListenerForSingleValueEvent(getUserNameValueEventListener);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            //Initial Post
            queryRefInitial.addListenerForSingleValueEvent(postValueEventListener);
            queryRefInitial.removeEventListener(postValueEventListener);            //Remove initial listener event after initial

            //Listener for database change
            queryRef.addValueEventListener(postValueEventListener);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public View PostToPostBox(Post _post, String _userName,LinearLayout _rootLayout){
        LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.post_box, _rootLayout ,false);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        TextView userName = view.findViewById(R.id.user_name);
        TextView date = view.findViewById(R.id.date);
        TextView content = view.findViewById(R.id.content);

        userName.setText(_userName);
        date.setText(dateFormat.format(_post.getDate()));
        content.setText(_post.getContent());

        return view;
    }

    public void AddPost(View v){
        Toast.makeText(getApplicationContext(),"Add new post",Toast.LENGTH_SHORT).show();
        Intent addPost = new Intent(this,AddPostActivity.class);
        startActivity(addPost);
    }

    @Override
    public  void onPause(){
        super.onPause();
        queryRef.removeEventListener(postValueEventListener);
    }

    @Override
    public void onResume(){
        super.onResume();
        queryRef.addValueEventListener(postValueEventListener);
    }

    public void ShowProfile(View v){
        Toast.makeText(getApplicationContext(),"Show profile",Toast.LENGTH_SHORT).show();
    }

    public void Logout(MenuItem mi){
        Toast.makeText(getApplicationContext(),"Logout",Toast.LENGTH_SHORT).show();
        auth.signOut();
        Intent tmp = new Intent(this,LoginActivity.class);
        startActivity(tmp);
    }

    public void Refresh(MenuItem mi){
        Toast.makeText(getApplicationContext(),"Refresh",Toast.LENGTH_SHORT).show();
    }

    public void Setting(MenuItem mi){
        Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
