package com.example.chota5511.testfirebase;

import android.support.annotation.NonNull;
import com.google.firebase.database.*;

import java.util.Date;

public class Post {
    private String postID;
    private String userUid;
    private Date date;
    private String content;

    private DatabaseReference db = FirebaseDatabase.getInstance().getReference("post");

    //Constructor
    public Post(){
        postID = new String();
        userUid = new String();
        date = new Date();
        content = new String();
    }
    public Post(String _userUid,Date _date,String _content){
        postID = new String();
        userUid = new String();
        date = new Date();
        content = new String();

        setUserUid(_userUid);
        setDate(_date);
        setContent(_content);
    }
    public Post(Post _post){
        postID = new String();
        userUid = new String();
        date = new Date();
        content = new String();

        setPostID(_post.getPostID());
        setUserUid(_post.getUserUid());
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
    public void setUserUid(String _userUid){
        userUid = _userUid;
    }
    public String getUserUid(){
        return userUid;
    }

    //Getter and Setter Date
    public void setDate(Date _date){
        date = _date;
    }
    public Date getDate(){
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
        if(userUid.isEmpty()==false&&date != null&&content.isEmpty()==false){
            if(postID.isEmpty() == true){
                setPostID(db.push().getKey());
            }
            db.child(getPostID()).child("uid").setValue(getUserUid());
            db.child(getPostID()).child("date").setValue(getDate());
            db.child(getPostID()).child("content").setValue(getContent());
            return true;
        }
        return false;
    }
}
