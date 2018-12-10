package com.example.chota5511.testfirebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (user == null){
            Intent login = new Intent(this,LoginActivity.class);
            startActivity(login);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Start code here
        LinearLayout postArea = findViewById(R.id.post_area);

        for (int i = 0; i<10; i++){
            Post p = new Post("chota5511", Calendar.getInstance().getTime(), String.valueOf(i));

            postArea.addView(PostToPostBox(p,postArea), 0);
        }
    }

    public View PostToPostBox(Post _post, LinearLayout _rootLayout){
        LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.post_box, _rootLayout ,false);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        TextView userName = view.findViewById(R.id.user_name);
        TextView date = view.findViewById(R.id.date);
        TextView content = view.findViewById(R.id.content);

        userName.setText(_post.getUserEmail());
        date.setText(dateFormat.format(_post.getDate()));
        content.setText(_post.getContent());

        return view;
    }

    public void AddPost(View v){
        Toast.makeText(getApplicationContext(),"Add new post",Toast.LENGTH_SHORT).show();
        Intent addPost = new Intent(this,AddPostActivity.class);
        startActivity(addPost);
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
