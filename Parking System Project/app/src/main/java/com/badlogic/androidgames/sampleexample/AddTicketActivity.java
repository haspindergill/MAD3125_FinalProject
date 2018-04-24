package com.badlogic.androidgames.sampleexample;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.badlogic.androidgames.sampleexample.database.AddTicket;
import com.badlogic.androidgames.sampleexample.database.AddTicketDao;
import com.badlogic.androidgames.sampleexample.database.AppDatabase;
import com.badlogic.androidgames.sampleexample.database.Student;
import com.badlogic.androidgames.sampleexample.database.StudentDao;

public class AddTicketActivity extends AppCompatActivity {

    TextView txtDate;
    TextView txtAmount;
    EditText edtVehicleNo;
    EditText edtVehicleBrand;
    RadioGroup rgTiming;
    RadioButton rbTiming;
    Spinner spColour;
    Spinner spLane;
    Spinner spPosition;
    Spinner spPayment;
    Button btnSave;



//    public AddTicketActivity(CallBack callBack){
//        this.mCallBack=callBack;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);
        setTitle("Add Ticket");

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        txtDate = (TextView) findViewById(R.id.txtDateTime);
        txtAmount = (TextView) findViewById(R.id.txtAmount);
        edtVehicleNo = (EditText) findViewById(R.id.edtVehicleNo);
        edtVehicleBrand = (EditText) findViewById(R.id.edtVehicleBrand);
        spColour = (Spinner) findViewById(R.id.spinnerColour);
        spLane = (Spinner) findViewById(R.id.spinnerLane);
        spPosition = (Spinner) findViewById(R.id.spinnerPosition);
        spPayment = (Spinner) findViewById(R.id.spinnerPayment);
        btnSave = (Button) findViewById(R.id.btnAddTicket);
        rgTiming = (RadioGroup) findViewById(R.id.rgTiming);
        int selectedId = rgTiming.getCheckedRadioButtonId();
        rbTiming = (RadioButton) findViewById(selectedId);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "AddTicket-database").build();
       // Toast.makeText(AddTicketActivity.this, String.valueOf(selectedId), Toast.LENGTH_LONG).show();
         txtAmount.setText("$5");
         txtDate.setText(formattedDate);
        rgTiming.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                if(i == R.id.rb1)
                {
                    txtAmount.setText("$5");
                }
                else if(i == R.id.rb2)
                {
                    txtAmount.setText("$10");
                }
                else if(i == R.id.rb3)
                {
                    txtAmount.setText("$15");
                }
                else if(i == R.id.rb4)
                {
                    txtAmount.setText("$20");
                }
                else
                {
                    txtAmount.setText("$25");
                }

            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AddTicketDao addTicketDao = AppDatabase.getInstance(AddTicketActivity.this).addTicket();
                final AddTicket at = new AddTicket();
                // st.setId(Integer.parseInt(edtStId.getText().toString()));
                at.setDate(txtDate.getText().toString());
                at.setAmount(txtAmount.getText().toString());
                at.setVehicleNo(edtVehicleNo.getText().toString());
                at.setVehicleBrand(edtVehicleBrand.getText().toString());
                at.setTiming(rbTiming.getText().toString());
                at.setColour(spColour.getSelectedItem().toString());
                at.setLane(spLane.getSelectedItem().toString());
                at.setPosition(spPosition.getSelectedItem().toString());
                at.setPayment(spPayment.getSelectedItem().toString());

                addTicketDao.insertNewAddTicket(at);

                if (at != null) {
                    Toast.makeText(AddTicketActivity.this, "Data successfully save ", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(AddTicketActivity.this, "Data not save ", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void my(){

    }
}
