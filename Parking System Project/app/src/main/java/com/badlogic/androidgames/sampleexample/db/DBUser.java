package com.badlogic.androidgames.sampleexample.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.badlogic.androidgames.sampleexample.LoginActivity;
import com.badlogic.androidgames.sampleexample.model.User;

import java.util.ArrayList;

/**
 * Created by macstudent on 2018-04-10.
 */

public class DBUser
{
    public static final String TABLE_USER = "tblUser";
    public static final String KEY_USER_EMAIL = "useremailid";
    public static final String KEY_USER_PASSWORD = "userpassword";
    private Context context;

    private DBHelper dbHelper;
    public DBUser(Context context)
    {
        this.context = context;
    }

    public void insertUser(User user)
    {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USER_EMAIL, user.getEmail());
        contentValues.put(KEY_USER_PASSWORD, user.getPassword());

        database.insert(TABLE_USER, null, contentValues);

        database.close();

    }

    public void updateUser(User user)
    {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USER_PASSWORD, user.getPassword());

        database.update(TABLE_USER,  contentValues, KEY_USER_EMAIL + "=?", new String[]{user.getEmail()});

        database.close();

    }


    public void deleteUser(User user)
    {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        database.delete(TABLE_USER, KEY_USER_EMAIL + "=?", new String[]{user.getEmail()});

        database.close();

    }

    public User getUser(String email){
        User user = new User();
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();


        Cursor cursor = database.query(TABLE_USER,
                null,
                null,
                null,
                null,
                null,
                null);

        if(cursor !=null)
        {
            if(cursor.getCount() > 0)
            {
                while (cursor.moveToNext())
                {

                    String userEmail = cursor.getString(0);
                    if (userEmail.equalsIgnoreCase(email)){
                    user.setEmail(cursor.getString(0));
                    user.setPassword(cursor.getString(1));
                    Log.d("DB",user.getEmail() + " : " + user.getPassword());
                    break;
                }
                }
            }
        }
        database.close();

        return user;
    }

    public ArrayList<User> getAllUser()
    {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.query(TABLE_USER,
                null,
                null,
                null,
                null,
                null,
                null);

        ArrayList<User> userArrayList = new ArrayList<>();
        if(cursor !=null)
        {
            if(cursor.getCount() > 0)
            {
                while (cursor.moveToNext())
                {
                    User user = new User();
                    user.setEmail(cursor.getString(0));
                    user.setPassword(cursor.getString(1));
                    userArrayList.add(user);
                    Log.d("DB",user.getEmail() + " : " + user.getPassword());
                }
            }
        }
        database.close();

        return userArrayList;

    }

    public boolean isValidUser(String email, String password)
    {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(TABLE_USER,
                null,
                KEY_USER_EMAIL + "=? AND " + KEY_USER_PASSWORD + "=?",
                new String[]{email, password},
                null,
                null,
                null);

        if(cursor !=null)
        {
            if(cursor.getCount() == 1)
            {
                return true;
            }
        }

        return false;
    }

}
