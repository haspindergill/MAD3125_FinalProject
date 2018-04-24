package com.badlogic.androidgames.sampleexample;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.badlogic.androidgames.sampleexample.database.AddTicketDao;
import com.badlogic.androidgames.sampleexample.database.AppDatabase;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txtUserName;
    TextView txtTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent  intent = new Intent(HomeActivity.this, AddTicketActivity.class);
                startActivity(intent);
            }
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                       // .setAction("Action", null).show()
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
      //  txtUserName = (TextView)findViewById(R.id.nav_name);

        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.txtUserName);
        TextView nav_email = (TextView)hView.findViewById(R.id.textView);


//txtUserName.setText("Welcome");
        SharedPreferences sharedPreferences = getSharedPreferences("mypref", MODE_PRIVATE);
        String userName = sharedPreferences.getString("userid", null);
        String name = sharedPreferences.getString("name", null);
//           Toast.makeText(HomeActivity.this, userName, Toast.LENGTH_LONG).show();
        nav_user.setText("Welcome");
        if(userName != null)
        {
           // if(drawer.isDrawerOpen(GravityCompat.START)) {
                nav_email.setText(userName);
               // nav_user.setText(userName);
           // }
        }

txtTotal =(TextView)findViewById(R.id.txtTotalTickets);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "AddTicket-database").build();
        AddTicketDao addTicketDao = AppDatabase.getInstance(HomeActivity.this).addTicket();

          //      List<AddTicket> stl = addTicketDao.getAllAddTicket();
                int total = addTicketDao.getAllAddTicket().size();
     //   Toast.makeText(HomeActivity.this, String.valueOf(total), Toast.LENGTH_LONG).show();
               txtTotal.setText(String.valueOf(total));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        UpdateMyText("");

    }

    public void UpdateMyText(String count){
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "AddTicket-database").build();
        AddTicketDao addTicketDao = AppDatabase.getInstance(HomeActivity.this).addTicket();

        //      List<AddTicket> stl = addTicketDao.getAllAddTicket();
        int total = addTicketDao.getAllAddTicket().size();
        //   Toast.makeText(HomeActivity.this, String.valueOf(total), Toast.LENGTH_LONG).show();
        txtTotal.setText(String.valueOf(total));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(HomeActivity.this,"Setting", Toast.LENGTH_LONG).show();
        }else if (id == R.id.action_save) {
            Toast.makeText(HomeActivity.this,"Save", Toast.LENGTH_LONG).show();
        }else if (id == R.id.action_1) {
            Toast.makeText(HomeActivity.this,"Option 1", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = null;
        if (id == R.id.nav_home)
        {
            intent = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_addTicket)
        {
            intent = new Intent(HomeActivity.this, AddTicketActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_location)
        {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:0,0?q=43.6497688362,-79.38952512778(" + getString(R.string.app_name) + ")"));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "Maps application is not available.", Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.nav_report)
        {
            intent = new Intent(HomeActivity.this, ReportActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_profile)
        {
            intent = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_instruction)
        {
            intent = new Intent(HomeActivity.this, InstructionActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_contact)
        {
            android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(HomeActivity.this);
            alertDialogBuilder.setTitle("Need Help?");
            alertDialogBuilder.setMessage("Call us at 911.Any Time?");
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialogBuilder.setPositiveButton("CAll", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                    // continue with discard
                    Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                    phoneIntent.setData(Uri.parse("tel:911"));

                }
            });
            alertDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {

                }
            });
            alertDialogBuilder.show();

        }
        else if (id == R.id.nav_logOut)
        { android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(HomeActivity.this);
            alertDialogBuilder.setTitle("Logout");
            alertDialogBuilder.setMessage("Are you sure?");
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialogBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                    finish();
//                    Intent i = new Intent(HomeActivity.this, LoginActivity.class);
//                    startActivity(i);
                }
            });
            alertDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {

                }
            });
            alertDialogBuilder.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

}



