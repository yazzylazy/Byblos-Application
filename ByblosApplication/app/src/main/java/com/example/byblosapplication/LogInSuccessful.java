package com.example.byblosapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class LogInSuccessful extends AppCompatActivity {

    String passedUsername, passedRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_successful);

        // gets the string for username and role that are passed along with the intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        passedUsername = extras.getString("USERNAME");
        passedRole = extras.getString("ROLE");

        // displays the username and role with a welcome message
        TextView logInSuccessfulMsg = (TextView) findViewById(R.id.logInSuccessfulMsg);
        logInSuccessfulMsg.setText("Welcome " + passedUsername + "!\n" + "You are signed in as\n" + passedRole + "!");

        // Timer that displays logInSuccessful for 3 seconds, then moves user to appropriate activity (employee to employee features, customer to customer features)
        new CountDownTimer(3000,1000) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if(passedRole.equals("admin")) {
                    Intent startAdminFeatures = new Intent(LogInSuccessful.this, AdminFeatures.class);
                    startActivity(startAdminFeatures);
                    finish();
                }
                else if(passedRole.equals("Employee")) {
                    Intent startEmployeeFeatures = new Intent(LogInSuccessful.this, EmployeeFeatures.class);
                    Bundle extras = new Bundle();
                    extras.putString("NAME", passedUsername);
                    startEmployeeFeatures.putExtras(extras);
                    startActivity(startEmployeeFeatures);
                    finish();
                }
                else {
                    Intent startCustomerFeatures = new Intent(LogInSuccessful.this, CustomerFeatures.class);
                    Bundle extras = new Bundle();
                    extras.putString("NAME", passedUsername);
                    startCustomerFeatures.putExtras(extras);
                    startActivity(startCustomerFeatures);
                    finish();
                }

            }
        }.start();



    }


}