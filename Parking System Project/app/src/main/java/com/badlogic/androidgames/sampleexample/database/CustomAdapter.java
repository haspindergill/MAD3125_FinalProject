package com.badlogic.androidgames.sampleexample.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.badlogic.androidgames.sampleexample.database.AddTicket;
import com.badlogic.androidgames.sampleexample.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haspinder on 18/04/18.
 */

public class CustomAdapter extends ArrayAdapter<AddTicket> {


    public CustomAdapter(Context context, List<AddTicket> employeeArrayList)
    {
        super(context, 0, employeeArrayList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Get the data item for this position
        AddTicket employee = getItem(position);
        ViewHolder viewHolder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.report_cell, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // Populate the data into the template view using the data object
        //viewHolder.tvEmployeeID.setText(String.valueOf(employee.getEmployeeId()));
        viewHolder.txtBrand.setText(employee.getVehicleBrand());
        viewHolder.txtDate.setText(employee.getDate());
        viewHolder.txtNum.setText(employee.getVehicleNo());
        viewHolder.txtPrice.setText(employee.getAmount());
        viewHolder.txtMethod.setText(employee.getPayment());
        viewHolder.txtLane.setText(employee.getColour() + "    " + employee.getLane() + "    " + employee.getPosition());


        return convertView;
    }

    private class ViewHolder
    {
        TextView txtDate;
        TextView txtBrand;
        TextView txtNum;
        TextView txtPrice;
        TextView txtLane;
        TextView txtMethod;

        ViewHolder(View convertView)
        {
            txtBrand = (TextView) convertView.findViewById(R.id.txtBrand);
            txtDate = (TextView) convertView.findViewById(R.id.txtDate);
            txtNum = (TextView) convertView.findViewById(R.id.txtNum);
            txtPrice = (TextView) convertView.findViewById(R.id.txtPrice);
            txtLane = (TextView) convertView.findViewById(R.id.txtLane);
            txtMethod = (TextView) convertView.findViewById(R.id.txtMethod);

            //tvEmployeeName = (TextView) convertView.findViewById(R.id.txtEmployeeName);
        }
    }
}
