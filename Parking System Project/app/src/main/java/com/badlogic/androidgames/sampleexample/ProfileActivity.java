package com.badlogic.androidgames.sampleexample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.badlogic.androidgames.sampleexample.db.DBUser;
import com.badlogic.androidgames.sampleexample.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = ProfileActivity.class.getName();
    @BindView(R.id.edtUserEmail)
    EditText edtName;
    @BindView(R.id.edtUserPassword)
    EditText edtPassword;
    @BindView(R.id.edtUserConfirmPassword)
    EditText edtConfirmPassword;
    @BindView(R.id.btnUpdate)
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        SharedPreferences preferences = getSharedPreferences("mypref",MODE_PRIVATE);

        String email = preferences.getString("userid","");
        String name = preferences.getString("name","");
        String pass = preferences.getString("password","");

        edtName.setText(email);
edtPassword.setText(pass);
        //DBUser dbUser = new DBUser(ProfileActivity.this);
        //User user = dbUser.getUser(email);
        //Log.d(TAG, user.getEmail());

    }


    @OnClick(R.id.btnUpdate)
    public void onViewClicked() {

        if (validate())
        {
            User user = new User();
            user.setEmail(edtName.getText().toString());
            user.setPassword(edtPassword.getText().toString());
            DBUser dbUser = new DBUser(ProfileActivity.this);
            dbUser.updateUser(user);
            SharedPreferences preferences = getSharedPreferences("mypref",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("id", 1);
            editor.putString("password",user.getPassword());
            editor.putString("userid",user.getEmail());
            editor.apply();
            Toast.makeText(ProfileActivity.this, "Profile Updated! ", Toast.LENGTH_LONG).show();

            finish();
        }
    }

    public boolean validate()
    {
        if(edtPassword.getText().toString().length() != 0)
        {
            if(edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString()))
            {
                return true;
            }
            else
            {
                edtConfirmPassword.setError("Confirm Password not matched");
            }
        }
        else
        {
            edtPassword.setError("Enter Password");
        }

        return false;

    }
}
