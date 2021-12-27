package com.example.byblosapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerFeatures extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_features);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String name = extras.getString("NAME");




        // create a service request
        Button btnRequest = (Button) findViewById(R.id.btnRequest);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListBranchAccounts.class);
                Bundle extras = new Bundle();
                extras.putString("style","customerRequest");
                extras.putString("NAME", name);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });


        // rating sends you to all branch accounts
        Button btnRate = (Button) findViewById(R.id.btnRate);
        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListBranchAccounts.class);
                Bundle extras = new Bundle();
                extras.putString("style","rate");
                extras.putString("NAME", name);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        // Sign out and go back to login page
        Button goToLogIn = (Button) findViewById(R.id.btnSignOutCustomer);
        goToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LogInPage.class);
                startActivity(intent);
            }
        });

        Button btnServiceRequestList = findViewById(R.id.btnViewServiceRequests);
        btnServiceRequestList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CustomerServiceRequestView.class);
                Bundle extras = new Bundle();
                extras.putString("NAME", name);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        Button btnViewAccountDetails = findViewById(R.id.btnViewAccountDetails);
            btnViewAccountDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ViewAccountDetails.class);
                    Bundle extras = new Bundle();
                    extras.putString("TYPE","Customer");
                    extras.putString("NAME", name);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            });




    }
}