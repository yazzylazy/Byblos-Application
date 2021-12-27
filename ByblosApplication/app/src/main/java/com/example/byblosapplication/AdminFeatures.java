package com.example.byblosapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminFeatures extends AppCompatActivity {
    Button createServiceButton, editServiceButton, deleteServiceButton;
    Button backButton;
    Button deleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_features);


        // go to create service
        createServiceButton = findViewById(R.id.btnCreateService);
        createServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateService.class);
                Bundle extras = new Bundle();
                extras.putString("TYPE","");
                extras.putString("SERVICE","");
                extras.putInt("HRATE",0);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        // go to edit service
        editServiceButton = findViewById(R.id.btnEditService);
        editServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListActiveServices.class);
                Bundle extras = new Bundle();
                extras.putString("TYPE", "edit");
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        // go to delete service
        deleteServiceButton = findViewById(R.id.btnDeleteService);
        deleteServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ListActiveServices.class);
                Bundle extras = new Bundle();
                extras.putString("TYPE", "delete");
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        // sign out and go back to login page
        backButton = findViewById(R.id.btnAdminServiceBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LogInPage.class);
                startActivity(intent);
            }
        });


        // go to delete account page
        deleteButton = findViewById(R.id.btnDeleteAccount);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),DeleteAccount.class);
                Bundle extras = new Bundle();
                extras.putBoolean("AccountDetails", false);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });


    }
}