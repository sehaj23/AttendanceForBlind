package com.example.sqliteinsertandfetch;

public class student {
    public String ID;
    public String USERNAME;



    public student(String ID,String USERNAME){
        this.ID = ID;
        this.USERNAME = USERNAME;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getID() {
        return ID;
    }
}



