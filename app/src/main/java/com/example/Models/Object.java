package com.example.Models;

public class Object {

    private int objectType;
    private int objectId;
    private String objectTitle;
    private String objectAddress;

    public Object (int id, int type, String title, String address){
        this.objectId=id;
        this.objectAddress=address;
        this.objectType=type;
        this.objectTitle=title;
    }

    public int getObjectType() {
        return objectType;
    }

    public int getObjectId() {
        return objectId;
    }

    public String getObjectTitle() {
        return objectTitle;
    }

    public String getObjectAddress() {
        return objectAddress;
    }
}
