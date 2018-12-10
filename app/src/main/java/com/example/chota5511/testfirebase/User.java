package com.example.chota5511.testfirebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User {
    private String userID;
    private String userName;
    private String password;

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference("user");

    //Constructor
    public User(){
        userID = new String();
        userName = new String();
        password = new String();
    }
    public User(String _userID, String _userName){
        userID = new String();
        userName = new String();
        password = new String();

        userID = _userID;
        userName = _userName;
    }
    public User(String _userID, String _userName,String _password){
        userID = new String();
        userName = new String();
        password = new String();

        userID = _userID;
        userName = _userName;
        password = _password;
    }
    public User(User _user){
        userID = new String();
        userName = new String();
        password = new String();

        userID = _user.userID;
        userName = _user.userName;
        password = _user.password;
    }

    //Getter and Setter User ID
    public void setUserID(String _userID){
        userID = _userID;
    }
    public String getUserID(){
        return userID;
    }

    //Getter and Setter User Name
    public void setUserName(String _userName){
        userName = _userName;
    }
    public String getUserName()
    {
        return userName;
    }

    //Getter and Setter Password
    public void setPassword(String _password){
        password = _password;
    }
    public String getPassword()
    {
        return password;
    }

    //Fetch user info
    public void fetchUser(String _userID){
        db.child(getUserID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userID = dataSnapshot.getKey();
                userName = dataSnapshot.child("username").getValue().toString();
                password = dataSnapshot.child("password").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Save user to database return true if success or false if failed
    public boolean SaveChange(){
        if(userID.isEmpty()==false&&userName.isEmpty()==false&&password.isEmpty()==false){
            db.child(getUserID()).child("username").setValue(getUserName());
            db.child(getUserID()).child("password").setValue(getPassword());
            return true;
        }
        return false;
    }
}
