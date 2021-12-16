package com.naeem.todoapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface profileDAO {

    @Query("SELECT * FROM profile")
    List<profile> getAll();

    @Insert
    void insert(profile profile);

    @Delete
    void delete(profile profile);

    @Update
    void update(profile profile);

}