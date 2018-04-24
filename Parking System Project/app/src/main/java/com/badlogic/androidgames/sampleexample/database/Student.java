package com.badlogic.androidgames.sampleexample.database;

/**
 * Created by macstudent on 2018-04-11.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.widget.EditText;

@Entity(tableName = "Student")
public class Student
{
   @PrimaryKey(autoGenerate = true)
    public int id;

   @ColumnInfo(name = "name")
    public String name;

    public int getId(int edtStId) {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(String edtStName) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + id +
                ", studentName='" + name + '\'' +
                '}';
    }
}
