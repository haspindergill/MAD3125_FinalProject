package com.badlogic.androidgames.sampleexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.badlogic.androidgames.sampleexample.db.DBUser;
import com.badlogic.androidgames.sampleexample.model.Employee;
import com.badlogic.androidgames.sampleexample.model.Student;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserId;
    EditText edtPassword;
    Button btnSignIn;
    Button btnSignUp;
    CheckBox chkRemeberMe;
    SharedPreferences myPref;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //1 - Create Shared Preferences Object
        myPref = getSharedPreferences("mypref",MODE_PRIVATE);

        edtUserId = (EditText) findViewById(R.id.edtUserName);
        //edtUserId.setTooltipText("Please enter User ID");
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        chkRemeberMe = (CheckBox)findViewById(R.id.chkRemeberMe);


        //2 - Get saved values from shared preferences
        String userid = myPref.getString("userid",null);
        String pwd = myPref.getString("password",null);

        //3 - Set values to Edit text
        if(userid != null && pwd != null) {
            edtUserId.setText(userid);
            edtPassword.setText(pwd);
            chkRemeberMe.setChecked(true);
        }else
        {
            chkRemeberMe.setChecked(false);
        }

        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(TextUtils.isEmpty(edtUserId.getText()) || edtUserId.getText().toString().length() == 0)
                {
                    edtUserId.setError("Please Enter User Name");
                }
                else {

                    DBUser dbUser = new DBUser(LoginActivity.this);
                    if (dbUser.isValidUser(edtUserId.getText().toString(), edtPassword.getText().toString()))
                    {
                        Toast.makeText(LoginActivity.this, "User Successfully logged in ", Toast.LENGTH_LONG).show();

                        //4 - Get editor object
                        SharedPreferences.Editor editor = myPref.edit();
                        if(chkRemeberMe.isChecked())
                        {
                            //5 - Save value to Shared Preferences using editor object
                            editor.putString("userid", edtUserId.getText().toString());
                            editor.putString("password", edtPassword.getText().toString());
                      //  editor.putString("name", dbUser.getUser(edtUserId.getText().toString()).getName().toString());
                        }
                        else
                        {
                            //6 - Remove values from shared preferences
                            editor.remove("userid");
                            editor.remove("password");
                        }
                        //7 - Save changes permanently to shared preferences
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        //Intent intent = new Intent(LoginActivity.this, StudentEntryActivity.class);
                        //startActivity(intent);
                        Toast.makeText(LoginActivity.this, "UserID/passwords invalid", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Student student = new Student();
                student.setStudentId(100);
                student.setStudentName("Mahesh");

                Employee employee = new Employee();
                employee.setEmployeeID(200);
                employee.setEmployeeName("Pritesh Patel");
                employee.setSalary(1000);
                employee.setGender(true);

                Bundle b = new Bundle();
                b.putString("name","Pritesh Patel");


                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                intent.putExtra("name","Pritesh Patel");
                intent.putExtra("age", 25);
                intent.putExtra("student", student);
                intent.putExtra("emp", employee);

                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }
}
