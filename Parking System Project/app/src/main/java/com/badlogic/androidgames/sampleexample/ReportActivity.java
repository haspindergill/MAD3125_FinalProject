package com.badlogic.androidgames.sampleexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.badlogic.androidgames.sampleexample.database.AddTicket;
import com.badlogic.androidgames.sampleexample.database.AddTicketDao;
import com.badlogic.androidgames.sampleexample.database.AppDatabase;
import com.badlogic.androidgames.sampleexample.database.CustomAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import android.widget.ListView;

public class ReportActivity extends AppCompatActivity {
    ListView list;

    List<AddTicket> tickets;
//    EmployeeAdapter employeeAdapter;
CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        setTitle("Report");



        AddTicketDao addTicketDao = AppDatabase.getInstance(ReportActivity.this).addTicket();
        tickets = addTicketDao.getAllAddTicket();
        list = (ListView) findViewById(R.id.list);
       // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, //Context
               // android.R.layout.simple_list_item_1, //Layout
               // tickets); ///Item list
        //Set Adapter to list view
       // list.setAdapter(arrayAdapter);
        adapter = new CustomAdapter(this,tickets);
        list.setAdapter(adapter);
    }
}
