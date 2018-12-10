package com.example.chota5511.testfirebase;

import android.widget.LinearLayout;
import com.google.firebase.database.*;
import java.util.Date;

public class Post {
    private String postID;
    private String userEmail;
    private String date;
    private String content;

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference("post");

    //Constructor
    public Post(){
        postID = new String();
        userEmail = new String();
        date = new String();
        content = new String();
    }
    public Post(String _userEmail,String _date,String _content){
        postID = new String();
        userEmail = new String();
        date = new String();
        content = new String();

        setUserEmail(_userEmail);
        setDate(_date);
        setContent(_content);
    }
    public Post(Post _post){
        postID = new String();
        userEmail = new String();
        date = new String();
        content = new String();

        setPostID(_post.getPostID());
        setUserEmail(_post.getUserEmail());
        setDate(_post.getDate());
        setContent(_post.getContent());
    }

    //Getter and Setter Content
    public void setPostID(String _postID){
        postID = _postID;
    }
    public String getPostID(){
        return postID;
    }

    //Setter and Getter User ID
    public void setUserEmail(String _userEmail){
        userEmail = _userEmail;
    }
    public String getUserEmail(){
        return userEmail;
    }

    //Getter User Name
    public String getUserName(){
        DatabaseReference tmpDB = FirebaseDatabase.getInstance().getReference("post");
        return tmpDB.child(getUserEmail()).child("username").getKey();
    }

    //Getter and Setter Date
    public void setDate(String _date){
        date = _date;
    }
    public String getDate(){
        return date;
    }

    //Getter and Setter Content
    public void setContent(String _content){
        content = _content;
    }
    public String getContent(){
        return content;
    }

    //Save post to database return true if success or false if failed
    public boolean SaveChange(){
        if(userEmail.isEmpty()==false&&date.isEmpty()==false&&content.isEmpty()==false){
            if(userEmail.isEmpty() == true){
                setUserEmail(db.push().getKey());
            }
            db.child(getPostID()).child("user_email").setValue(getUserEmail());
            db.child(getPostID()).child("date").setValue(getDate());
            db.child(getUserEmail()).child("content").setValue(getContent());
            return true;
        }
        return false;
    }
}
