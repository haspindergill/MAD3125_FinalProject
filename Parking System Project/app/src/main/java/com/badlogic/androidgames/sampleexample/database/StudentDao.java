package com.badlogic.androidgames.sampleexample.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * Created by macstudent on 2018-04-11.
 */

import java.util.List;
import java.util.jar.Attributes;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface StudentDao
{
   @Query("Select * from Student")
    public List<Student> getAllStudent();



   @Insert(onConflict = REPLACE)
    public void insertNewStudent(Student student);
}
