package com.naeem.todoapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "profile")
public class profile {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "firstName")
    private String firstName;

    @ColumnInfo(name = "lastName")
    private String lastName;

    @ColumnInfo(name = "email")
    private String email;




    /*
     * Getters and Setters
     * */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setDesc(String desc) {
        this.lastName = desc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String finishBy) {
        this.email = email;
    }
    public profile(String firstName ,String lastName ,String email){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
    }


}