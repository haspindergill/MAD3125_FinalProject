package com.badlogic.androidgames.sampleexample.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface AddTicketDao
{


    @Query("Select * from AddTicket")
    public List<AddTicket> getAllAddTicket();

    @Insert(onConflict = REPLACE)
    public void insertNewAddTicket(AddTicket addTicket);


}
