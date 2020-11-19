package com.example.TraSeApp.model;


public class Post {
    private String username, time , uid;
    private int likeCounter,cmtCounter ,imgPost, avt;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getLikeCounter() {
        return likeCounter;
    }

    public void setLikeCounter(int likeCounter) {
        this.likeCounter = likeCounter;
    }

    public int getImgPost() {
        return imgPost;
    }

    public void setImgPost(int imgPost) {
        this.imgPost = imgPost;
    }

    public int getAvt() {
        return avt;
    }

    public void setAvt(int avt) {
        this.avt = avt;
    }

    public int getCmtCounter() {
        return cmtCounter;
    }

    public void setCmtCounter(int cmtCounter) {
        this.cmtCounter = cmtCounter;
    }

    public Post(String username, String time, String uid, int imgPost, int avt, int likeCounter, int cmtCounter) {
        this.username = username;
        this.time = time;
        this.uid = uid;
        this.likeCounter = likeCounter;
        this.imgPost = imgPost;
        this.avt = avt;
        this.cmtCounter = cmtCounter;
    }

    public Post() {
    }




}
